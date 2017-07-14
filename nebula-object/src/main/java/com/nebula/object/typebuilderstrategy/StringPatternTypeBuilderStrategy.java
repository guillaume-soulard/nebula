package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.NumberList;
import com.nebula.object.annotation.type.StringPattern;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import static com.nebula.core.NebulaGenerationTypes.number;
import static com.nebula.core.NebulaGenerationTypes.string;

public class StringPatternTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        StringPattern stringPattern = (StringPattern) typeAnnotationOfField;
        return string().withPattern(stringPattern.pattern());
    }
}
