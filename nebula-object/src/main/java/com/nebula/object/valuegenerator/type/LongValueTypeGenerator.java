package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import com.nebula.object.valuegenerator.ValueTypeGeneratorContext;

import java.math.BigDecimal;
import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class LongValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Long.class.equals(clazz) || long.class.equals(clazz);
    }

    @Override
    public Object getValue(ValueTypeGeneratorContext context) {
        return context.getObjectGenerator().getObjectGeneratorBuilder().getDefaultLong();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(BigDecimal.valueOf(Long.MIN_VALUE))
                .withMax(BigDecimal.valueOf(Long.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Long.valueOf(object.toString());
    }
}
