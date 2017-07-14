package com.nebula.object.fieldsetter;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class NumberFieldSetter extends AbstractFieldSetter<BigDecimal> {

    @Override
    protected void setObjectValue(Object object, Field field, BigDecimal value) throws IllegalAccessException {
        if (field.getType().getName().equals("int")) {
            field.set(object, value.intValue());
        } else if (field.getType().getName().equals("long")) {
            field.set(object, value.longValue());
        } else if (field.getType().getName().equals("double")) {
            field.set(object, value.doubleValue());
        } else if (field.getType().getName().equals("float")) {
            field.set(object, value.floatValue());
        } else {
            field.set(object, value);
        }
    }
}
