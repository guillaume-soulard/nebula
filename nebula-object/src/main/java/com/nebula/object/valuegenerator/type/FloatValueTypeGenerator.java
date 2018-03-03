package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class FloatValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Float.class.equals(clazz) || float.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Float.toString(Float.MIN_VALUE))
                .withMax(Float.toString(Float.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Float.valueOf(object.toString());
    }
}
