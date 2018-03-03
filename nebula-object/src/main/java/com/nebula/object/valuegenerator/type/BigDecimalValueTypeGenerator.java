package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.math.BigDecimal;
import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class BigDecimalValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return BigDecimal.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(BigDecimal.valueOf(Long.MIN_VALUE))
                .withMax(BigDecimal.valueOf(Long.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return new BigDecimal(object.toString());
    }
}
