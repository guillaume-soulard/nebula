package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.util.ArrayList;
import java.util.List;

public class ListValueTypeGenerator extends ValueTypeGeneratorWithMaxDepth {

    @Override
    public boolean match(Class<?> clazz) {
        return clazz.isAssignableFrom(List.class);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultCollectionSize(); i++) {
            list.add(generateIfMaxDepthNotReached(context, context.getTypeArguments().get(0)));
        }
        return list;
    }
}
