package com.nebula.object.fieldsetter;

import java.lang.reflect.Field;

public class ObjectFieldSetter extends AbstractFieldSetter<Object> {

    @Override
    protected void setObjectValue(Object object, Field field, Object value) throws IllegalAccessException {
        field.set(object, value);
    }
}