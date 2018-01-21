package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class DoubleValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Double.class.equals(clazz) || double.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultDouble();
    }
}
