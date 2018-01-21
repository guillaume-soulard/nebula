package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class IntegerValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return Integer.class.equals(clazz) || int.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultInt();
    }
}
