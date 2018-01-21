package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

public class ShortValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return short.class.equals(clazz) || Short.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultShort();
    }
}
