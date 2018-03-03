package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.number;

public class ByteValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return byte.class.equals(clazz) || Byte.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return number().range()
                .withMin(Byte.toString(Byte.MIN_VALUE))
                .withMax(Byte.toString(Byte.MAX_VALUE));
    }

    @Override
    public Object getFinalValue(Object object) {
        return Byte.valueOf(object.toString());
    }
}
