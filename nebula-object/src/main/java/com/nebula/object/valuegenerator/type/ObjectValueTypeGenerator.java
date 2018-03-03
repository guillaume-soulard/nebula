package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.entity;

public class ObjectValueTypeGenerator extends ValueTypeGeneratorWithMaxDepth {

    @Override
    public boolean match(Class<?> clazz) {
        return true;
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return generateIfMaxDepthNotReached(context, context.getClazz());
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return entity(type.getCanonicalName());
    }

    @Override
    public Object getFinalValue(Object object) {
        return object;
    }
}
