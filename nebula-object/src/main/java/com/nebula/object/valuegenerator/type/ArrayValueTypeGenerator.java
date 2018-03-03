package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.lang.reflect.Array;
import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.entity;
import static com.nebula.core.types.NebulaTypes.list;

public class ArrayValueTypeGenerator extends ValueTypeGeneratorWithMaxDepth {

    @Override
    public boolean match(Class<?> clazz) {
        return clazz.isArray();
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        Object[] array = (Object[]) Array.newInstance(context.getClazz().getComponentType(),
                context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultCollectionSize());
        for (int i = 0; i < 10; i++) {
            array[i] = generateIfMaxDepthNotReached(context, context.getClazz().getComponentType());
        }
        return array;
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return list().of(random(), entity(type.getComponentType().getCanonicalName()));
    }

    @Override
    public Object getFinalValue(Object object) {
        return object;
    }
}
