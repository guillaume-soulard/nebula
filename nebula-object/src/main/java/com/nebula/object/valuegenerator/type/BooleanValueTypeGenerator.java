package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class BooleanValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().isDefaultBoolean();
    }
}
