package com.nebula.object.valuegenerator;

public interface ValueTypeGenerator {
    boolean match(Class<?> clazz);
    Object getValue(ValueTypeGeneratorContext context);
}
