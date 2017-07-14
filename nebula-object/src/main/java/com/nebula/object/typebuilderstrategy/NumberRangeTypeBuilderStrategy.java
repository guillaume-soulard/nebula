package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.NumberRange;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import static com.nebula.core.NebulaGenerationTypes.number;

public class NumberRangeTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        NumberRange numberRange = (NumberRange) typeAnnotationOfField;
        return number().range()
                .withMin(new BigDecimal(numberRange.min()))
                .withMax(new BigDecimal(numberRange.max()))
                .withPrecision(numberRange.precision());
    }
}
