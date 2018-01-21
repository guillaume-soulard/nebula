package com.nebula.object.valuegenerator;

import com.nebula.object.generator.SimpleObjectGenerator;

import java.util.List;

public class ValueTypeGeneratorContext {
    private Class<?> clazz;
    private List<Class<?>> typeArguments;
    private SimpleObjectGenerator objectGenerator;
    private int currentGenerationDepth;

    public ValueTypeGeneratorContext(Class<?> clazz, List<Class<?>> typeArguments, int currentGenerationDepth, SimpleObjectGenerator objectGenerator) {
        this.clazz = clazz;
        this.typeArguments = typeArguments;
        this.objectGenerator = objectGenerator;
        this.currentGenerationDepth = currentGenerationDepth;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public List<Class<?>> getTypeArguments() {
        return typeArguments;
    }

    public SimpleObjectGenerator getObjectGenerator() {
        return objectGenerator;
    }

    public int getCurrentGenerationDepth() {
        return currentGenerationDepth;
    }

    public void incrementGenerationDepth() {
        currentGenerationDepth++;
    }
}
