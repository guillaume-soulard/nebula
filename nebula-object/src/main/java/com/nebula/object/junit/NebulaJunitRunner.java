package com.nebula.object.junit;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.object.generator.ModelBasedObjectGenerator;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

class NebulaJunitRunner extends Runner implements Filterable {

    private final BlockJUnit4ClassRunner runner;

    public NebulaJunitRunner(Class testClass) throws InitializationError {
        runner = new BlockJUnit4ClassRunner(testClass) {

            protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {

                try {
                    for (Field field : FieldUtils.getFieldsWithAnnotation(target.getClass(), Generate.class)) {
                        Generate generateAnnotation = field.getDeclaredAnnotation(Generate.class);
                        field.setAccessible(true);
                        Model model = getModelByNameOrDefault(field, generateAnnotation.usingModel(), target);
                        ModelBasedObjectGenerator objectGenerator = new ModelBasedObjectGenerator(model);

                        if (Collection.class.isAssignableFrom(field.getType())) {
                            Class<?> type = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                            field.set(target, objectGenerator.generateListOf(generateAnnotation.amount(), type));
                        } else {
                            field.set(target, objectGenerator.generateNext(field.getType()));
                        }
                    }


                } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                return super.withBefores(method, target, statement);
            }

            private Model getModelByNameOrDefault(Field field, String modelName, Object target) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {

                Method modelMethod = null;

                Class<?> modelType = field.getType();
                if (Collection.class.isAssignableFrom(field.getType())) {
                    modelType = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                }

                Model defaultModel = new ClassModelBuilder().buildModelFrom(modelType);

                if ("default".equals(modelName)) {
                    return defaultModel;
                }

                for (Method typeMethod : MethodUtils.getMethodsListWithAnnotation(target.getClass(), GenerationModel.class)) {
                    GenerationModel modelAnnotation = typeMethod.getDeclaredAnnotation(GenerationModel.class);
                    if (modelAnnotation != null && modelAnnotation.name().equals(modelName)) {
                        if (!Model.class.equals(typeMethod.getReturnType())
                                || typeMethod.getParameters().length != 1
                                || !Model.class.equals(typeMethod.getParameters()[0].getType())) {
                            throw new NebulaException("Method " + typeMethod.toGenericString() + " must be like " + Model.class.toGenericString() + " " + typeMethod.getName() + " (" + Model.class.toGenericString() + " model)");
                        }

                        if (modelMethod != null) {
                            throw new NebulaException("Found duplicate model definition for '" + modelName + "'");
                        }

                        modelMethod = typeMethod;
                    }
                }


                if (modelMethod == null) {

                    throw new NebulaException("Model '" + modelName + "' not found");
                }

                return (Model) modelMethod.invoke(target, defaultModel);
            }
        };
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }

    @Override
    public void run(RunNotifier runNotifier) {
        runner.run(runNotifier);
    }

    @Override
    public void filter(Filter filter) throws NoTestsRemainException {
        runner.filter(filter);
    }
}
