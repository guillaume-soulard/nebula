package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;

import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.entity;
import static com.nebula.core.types.NebulaTypes.list;

public class ArrayValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return clazz.isArray();
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return list().of(random(), entity(type.getComponentType().getCanonicalName()));
    }

    @Override
    public Object getFinalValue(Object object) {
        return object;
    }
}
