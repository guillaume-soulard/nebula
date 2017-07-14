package com.nebula.object.fieldsetter;

import org.joda.time.DateTime;

import java.lang.reflect.Field;

public class DateFieldSetter extends AbstractFieldSetter<DateTime> {

    @Override
    protected void setObjectValue(Object object, Field field, DateTime value) throws IllegalAccessException {

        if (field.getType().getName().equals("java.util.Date")) {
            field.set(object, value.toDate());
        } else {
            field.set(object, value);
        }

    }
}