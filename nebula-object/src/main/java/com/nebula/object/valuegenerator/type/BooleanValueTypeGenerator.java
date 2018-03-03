package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.util.List;

import static com.nebula.core.types.NebulaTypes.bool;

public class BooleanValueTypeGenerator implements ValueTypeGenerator {
    @Override
    public boolean match(Class<?> clazz) {
        return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return bool();
    }

    @Override
    public Object getFinalValue(Object object) {
        return Boolean.valueOf(object.toString());
    }
}
