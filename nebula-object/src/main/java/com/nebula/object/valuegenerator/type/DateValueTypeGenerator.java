package com.nebula.object.valuegenerator.type;

import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.object.valuegenerator.ValueTypeGenerator;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

import static com.nebula.core.types.NebulaTypes.dateTime;

public class DateValueTypeGenerator implements ValueTypeGenerator {

    @Override
    public boolean match(Class<?> clazz) {
        return Date.class.equals(clazz) || DateTime.class.equals(clazz);
    }

    @Override
    public RandomTypeBuilder getNebulaTypeFor(Class<?> type, List<Class<?>> genericTypes) {
        return dateTime().range();
    }

    @Override
    public Object getFinalValue(Object object) {

        if (object instanceof DateTime) {
            return ((DateTime) object).toDate();
        }

        return object;
    }
}
