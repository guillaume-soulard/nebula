package com.nebula.object.generator;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.object.ObjectGenerator;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.type.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class ModelBasedObjectGenerator implements ObjectGenerator {

    private final List<ValueTypeGenerator> valueTypeGenerators;
    private final Model model;
    private final AtomicLong index = new AtomicLong(0L);

    public ModelBasedObjectGenerator(Model model) {
        this.model = model;

        List<ValueTypeGenerator> basicValueTypeGenerators = new ArrayList<>();

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

    @Override
    public <T> T generateNext(Class<T> clazz) {

        return generateAt(index.getAndIncrement(), clazz);
    }

    @Override
    public <T> T generateAt(long index, Class<T> clazz) {

        GeneratedObject generatedObject = model.generateEntityObject(clazz.getCanonicalName(), index);
        return convertToObject(clazz, generatedObject);
    }

    @Override
    public <T> List<T> generateListOf(int amount, Class<T> clazz) {
        List<T> list = new ArrayList<>();

        IntStream.range(0, amount).forEach((index) -> list.add(generateNext(clazz)));

        return list;
    }

    @Override
    public <T> List<T> generateLazyListOf(int amount, Class<T> clazz) {

        return new LazyList<>(amount, this, clazz);
    }

    @SuppressWarnings("unchecked")
    private <T> T convertToObject(Class<T> clazz, GeneratedObject generatedObject) {
        try {

            if (isBasicGeneratedObject(generatedObject)) {
                return (T) generatedObject.getGeneratedPropertyValue("_val").getObject();
            }

            if (generatedObject.getGeneratedProperties() != null) {
                T instance = clazz.newInstance();
                for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {

                    Field field = FieldUtils.getDeclaredField(clazz, generatedProperty.getPropertyName(),  true);
                    if (field != null && generatedProperty.getPropertyValue() != null) {
                        if (generatedProperty.getPropertyValue().getGeneratedProperties() != null) {
                            Object value = getFinalValueFrom(field.getType(), convertToObject(field.getType(), generatedProperty.getPropertyValue()));
                            field.set(instance, value);
                        } else {
                            if (field.getType().isArray()) {
                                convertArray(instance, generatedProperty, field);
                            } else if (Collection.class.isAssignableFrom(field.getType())) {
                                convertCollection(instance, generatedProperty, field);
                            } else {
                                field.set(instance, getFinalValueFrom(field.getType(), generatedProperty.getPropertyValue().getObject()));
                            }
                        }
                    }
                }
                return instance;
            } else {
                return (T) generatedObject.getObject();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    private <T> void convertArray(T instance, GeneratedProperty generatedProperty, Field field) throws IllegalAccessException {
        Collection<Object> array = (Collection<Object>) generatedProperty.getPropertyValue().getObject();

        Object convertedArray = Array.newInstance(field.getType().getComponentType(), array.size());

        int i = 0;

        for (Object object : array) {
            Array.set(convertedArray, i, convertToObject(field.getType().getComponentType(), (GeneratedObject) object));
            i++;
        }

        field.set(instance, convertedArray);
    }

    private <T> void convertCollection(T instance, GeneratedProperty generatedProperty, Field field) throws IllegalAccessException {
        Collection<Object> array = (Collection<Object>) generatedProperty.getPropertyValue().getObject();

        List<Object> convertedList = new ArrayList<>();

        for (Object object : array) {
            try {
                convertedList.add(convertToObject(Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName()), (GeneratedObject) object));
            } catch (ClassNotFoundException e) {
                throw new NebulaException(e.getMessage());
            }
        }

        field.set(instance, convertedList);
    }

    private boolean isBasicGeneratedObject(GeneratedObject generatedObject) {
        return generatedObject.getGeneratedProperties() != null
                && generatedObject.getGeneratedProperties()
                .stream().anyMatch(generatedProperty -> "_val".equals(generatedProperty.getPropertyName()));
    }

    private Object getFinalValueFrom(Class<?> fieldType, Object object) {

        for (ValueTypeGenerator valueTypeGenerator : valueTypeGenerators) {
            if (valueTypeGenerator.match(fieldType)) {
                return valueTypeGenerator.getFinalValue(object);
            }
        }

        throw new NebulaException("Type " + object.getClass().toGenericString() + " is not supported");
    }
}
