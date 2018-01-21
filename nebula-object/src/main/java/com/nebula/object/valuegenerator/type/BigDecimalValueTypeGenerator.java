package com.nebula.object.valuegenerator.type;

import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.math.BigDecimal;

public class BigDecimalValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return BigDecimal.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultBigDecimal();
    }
}
