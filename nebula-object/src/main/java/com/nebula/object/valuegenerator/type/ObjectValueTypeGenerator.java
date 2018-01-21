package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class ObjectValueTypeGenerator extends ValueTypeGeneratorWithMaxDepth {

    @Override
    public boolean match(Class<?> clazz) {
        return true;
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return generateIfMaxDepthNotReached(context, context.getClazz());
    }
}
