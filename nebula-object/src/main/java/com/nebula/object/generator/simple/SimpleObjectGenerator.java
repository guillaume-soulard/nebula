package com.nebula.object.generator.simple;

import com.nebula.core.NebulaException;
import com.nebula.object.ObjectGenerator;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;
import com.nebula.object.valuegenerator.type.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SimpleObjectGenerator implements ObjectGenerator {

    private List<ValueTypeGenerator> valueTypeGenerators;
    private List<ValueTypeGenerator> basicValueTypeGenerators;
    private ObjectGeneratorBuilder objectGeneratorBuilder;

    SimpleObjectGenerator(ObjectGeneratorBuilder objectGeneratorBuilder) {
        this.objectGeneratorBuilder = objectGeneratorBuilder;
        basicValueTypeGenerators = new ArrayList<>();

        basicValueTypeGenerators.add(new BigDecimalValueTypeGenerator());
        basicValueTypeGenerators.add(new BooleanValueTypeGenerator());
        basicValueTypeGenerators.add(new DateValueTypeGenerator());
        basicValueTypeGenerators.add(new DoubleValueTypeGenerator());
        basicValueTypeGenerators.add(new IntegerValueTypeGenerator());
        basicValueTypeGenerators.add(new StringValueTypeGenerator());
        basicValueTypeGenerators.add(new FloatValueTypeGenerator());
        basicValueTypeGenerators.add(new LongValueTypeGenerator());
        basicValueTypeGenerators.add(new ShortValueTypeGenerator());
        basicValueTypeGenerators.add(new CharValueTypeGenerator());
        basicValueTypeGenerators.add(new ByteValueTypeGenerator());

        valueTypeGenerators = new ArrayList<>();

        valueTypeGenerators.addAll(basicValueTypeGenerators);
        valueTypeGenerators.add(new ListValueTypeGenerator(basicValueTypeGenerators));
        valueTypeGenerators.add(new ArrayValueTypeGenerator());

        // the last field setter to test
        valueTypeGenerators.add(new ObjectValueTypeGenerator());
    }

    public <T> T generateWithMaxDepth(Class<T> clazz, int currentGenerationDepth) {
        try {

            if (isBasicType(clazz)) {
                return generateBasicType(clazz, currentGenerationDepth);
            } else {

                T instance = clazz.newInstance();

                List<Field> fields = FieldUtils.getAllFieldsList(clazz);

                for (Field field : fields) {
                    setValueInObjectForField(instance, field, currentGenerationDepth);
                }

                return instance;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    public <T> T generateNext(Class<T> clazz) {
        return generateWithMaxDepth(clazz, 1);
    }

    public <T> List<T> generateListOf(int amount, Class<T> clazz) {

        if (amount < 0) {
            throw new NebulaException("amount is negative");
        }


        List<T> list = new ArrayList<>();

        for (int i = 1; i <= amount; i++) {
            list.add(generateNext(clazz));
        }

        return list;
    }

    private <T> T generateBasicType(Class<T> clazz, int currentGenerationDepth) {
        for (ValueTypeGenerator basicValueTypeGenerator : basicValueTypeGenerators) {
            if (basicValueTypeGenerator.match(clazz)) {
                return (T) basicValueTypeGenerator.getValue(new ValueTypeGeneratorContext(clazz, new ArrayList<>(), currentGenerationDepth, this));
            }
        }

        throw new NebulaException("No value type generator matches for " + clazz);
    }

    private <T> boolean isBasicType(Class<T> clazz) {
        for (ValueTypeGenerator generator : basicValueTypeGenerators) {
            if (generator.match(clazz)) {
                return true;
            }
        }

        return false;
    }

    private <T> void setValueInObjectForField(T instance, Field field, int currentGenerationDepth) throws IllegalAccessException {

        ValueTypeGenerator valueGeneratorToUse = null;

        for (ValueTypeGenerator generator : valueTypeGenerators) {
            if (generator.match(field.getType())) {
                valueGeneratorToUse = generator;
                break;
            }
        }

        if (valueGeneratorToUse != null) {
            boolean wasAccessible = true;
            if (!field.isAccessible()) {
                field.setAccessible(true);
                wasAccessible = false;
            }
            List<Class<?>> typeArguments = new ArrayList<>();

            if (field.getGenericType() instanceof ParameterizedType) {
                ParameterizedType typeClass = (ParameterizedType) field.getGenericType();
                for (Type type : typeClass.getActualTypeArguments()) {
                    typeArguments.add((Class<?>) type);
                }
            }

            Object valueToSet = valueGeneratorToUse.getValue(new ValueTypeGeneratorContext(field.getType(), typeArguments, currentGenerationDepth, this));
            field.set(instance, valueToSet);
            if (!wasAccessible) {
                field.setAccessible(false);
            }
        } else {
            throw new NebulaException("No field setter for " + field.toString());
        }
    }

    public ObjectGeneratorBuilder getObjectGeneratorBuilder() {
        return objectGeneratorBuilder;
    }
}
