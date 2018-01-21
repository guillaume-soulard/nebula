package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.lang.reflect.Array;

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
}
