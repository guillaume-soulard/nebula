package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface TypeBuilderStrategy {
    TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField);
}
