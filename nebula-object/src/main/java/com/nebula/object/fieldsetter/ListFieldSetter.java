package com.nebula.object.fieldsetter;

import java.lang.reflect.Field;
import java.util.List;

public class ListFieldSetter extends AbstractFieldSetter<List<Object>> {
    @Override
    protected void setObjectValue(Object object, Field field, List<Object> value) throws IllegalAccessException {
        if (field.getType().isArray()) {
            field.set(object, value.toArray());
        } else {
            field.set(object, value);
        }
    }
}
