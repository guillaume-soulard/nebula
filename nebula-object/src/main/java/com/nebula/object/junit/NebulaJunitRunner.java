package com.nebula.object.junit;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.object.generator.ModelBasedObjectGenerator;
import com.nebula.object.generator.model.ClassModelBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class NebulaJunitRunner implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) {

        try {
            for (Field field : FieldUtils.getFieldsWithAnnotation(extensionContext.getRequiredTestClass(), Generate.class)) {
                Generate generateAnnotation = field.getDeclaredAnnotation(Generate.class);
                field.setAccessible(true);
                Model model = getModelByNameOrDefault(field, generateAnnotation.usingModel(), extensionContext.getRequiredTestInstance());
                ModelBasedObjectGenerator objectGenerator = new ModelBasedObjectGenerator(model);

                if (Collection.class.isAssignableFrom(field.getType())) {
                    Class<?> type = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                    field.set(extensionContext.getRequiredTestInstance(), objectGenerator.generateListOf(generateAnnotation.amount(), type));
                } else {
                    field.set(extensionContext.getRequiredTestInstance(), objectGenerator.generateNext(field.getType()));
                }
            }


        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
}
