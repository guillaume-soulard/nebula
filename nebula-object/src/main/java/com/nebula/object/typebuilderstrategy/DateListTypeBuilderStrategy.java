package com.nebula.object.typebuilderstrategy;

import com.nebula.core.types.TypeBuilder;
import com.nebula.object.annotation.type.DateList;
import com.nebula.object.annotation.type.DateRange;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static com.nebula.core.NebulaGenerationTypes.dateTime;

public class DateListTypeBuilderStrategy implements TypeBuilderStrategy {
    @Override
    public TypeBuilder getTypeBuilder(Field field, Annotation typeAnnotationOfField) {
        // FIXME date format
        DateList dateList = (DateList) typeAnnotationOfField;
        return dateTime().among().items(getDates(dateList.value()));
    }

    private DateTime[] getDates(String[] dateStrings) {
        DateTime[] dates = new DateTime[dateStrings.length];
        int i = 0;

        for (String dateString : dateStrings) {
            dates[i] = DateTime.parse(dateString, DateTimeFormat.forPattern("dd/MM/yyyy"));
        }

        return dates;
    }
}
