package com.nebula.object.junit;

import com.nebula.core.NebulaException;
import com.nebula.object.ObjectGenerator;
import com.nebula.object.generator.simple.ObjectGeneratorBuilder;
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
import java.util.HashMap;
import java.util.Map;

public class NebulaJunitRunner extends Runner implements Filterable {

    private final BlockJUnit4ClassRunner runner;

    public NebulaJunitRunner(Class testClass) throws InitializationError {
        runner = new BlockJUnit4ClassRunner(testClass) {

            private Map<String, com.nebula.core.Model> models = new HashMap<>();

            protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {

                try {
                    for (Method typeMethod : MethodUtils.getMethodsListWithAnnotation(target.getClass(), Model.class)) {
                        Model modelAnnotation = typeMethod.getDeclaredAnnotation(Model.class);
                        if (modelAnnotation != null) {
                            if (!com.nebula.core.Model.class.equals(typeMethod.getReturnType())) {
                                throw new NebulaException("Method " + typeMethod.toGenericString() + " must return " + com.nebula.core.Model.class.toGenericString());
                            }

                            models.put(modelAnnotation.name(), (com.nebula.core.Model) typeMethod.invoke(target));
                        }
                    }


                    ObjectGeneratorBuilder builder = new ObjectGeneratorBuilder();
                    ObjectGenerator objectGenerator = builder.build();

                    for (Field field : FieldUtils.getFieldsWithAnnotation(target.getClass(), Generate.class)) {
                        Generate populateAnnotation = field.getDeclaredAnnotation(Generate.class);
                        boolean fieldWasAccessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(target, objectGenerator.generateNext(field.getType()));
                        field.setAccessible(fieldWasAccessible);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                return super.withBefores(method, target, statement);
            }

            public void run(final RunNotifier notifier) {
                notifier.addListener(new NebulaRunListener());;
                super.run(notifier);
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
