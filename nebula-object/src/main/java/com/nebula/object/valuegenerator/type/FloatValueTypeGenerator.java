package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class FloatValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Float.class.equals(clazz) || float.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultFloat();
    }
}
