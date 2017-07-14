package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.nebula.core.NebulaGenerationTypes.bool;

public class BoolTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        return bool();
    }
}
