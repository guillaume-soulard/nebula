package com.nebula.object.valuegenerator;

import com.nebula.core.types.RandomTypeBuilder;

import java.util.List;

public interface ValueTypeGenerator {
    boolean match(Class<?> clazz);
    RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes);
    Object getFinalValue(Object object);
}