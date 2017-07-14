package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.DateRange;
import com.nebula.object.annotation.type.StringPattern;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.nebula.core.NebulaGenerationTypes.dateTime;
import static com.nebula.core.NebulaGenerationTypes.string;

public class DateRangeTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        // FIXME date format
        DateRange dateRange = (DateRange) typeAnnotationOfField;
        return dateTime().range()
                .withInterval(dateRange.interval())
                .withMin(DateTime.parse(dateRange.min(), DateTimeFormat.forPattern("dd/MM/yyyy")))
                .withMax(DateTime.parse(dateRange.min(), DateTimeFormat.forPattern("dd/MM/yyyy")));
    }
}
