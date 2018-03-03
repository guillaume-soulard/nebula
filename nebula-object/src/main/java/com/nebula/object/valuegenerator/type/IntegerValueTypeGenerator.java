package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class IntegerValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return Integer.class.equals(clazz) || int.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Integer.toString(Integer.MIN_VALUE))
                .withMax(Integer.toString(Integer.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Integer.valueOf(object.toString());
    }
}
