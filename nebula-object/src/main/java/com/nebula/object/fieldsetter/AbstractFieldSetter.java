package com.nebula.object.fieldsetter;

import com.nebula.core.NebulaException;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public abstract class AbstractFieldSetter<T> implements FieldSetter {

    public void setValueToObjectForField(Object object, Field field, Object value) {
        try {
            setFieldAccessibleIfNeeded(field);
            if (allObjectAndFieldAreNotNull(object, field)) {
                setObjectValue(object, field, (T) value);
            }
        } catch (IllegalAccessException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    protected abstract void setObjectValue(Object object, Field field, T value) throws IllegalAccessException;

    private boolean allObjectAndFieldAreNotNull(Object object, Field field) {
        return object != null && field != null;
    }

    private void setFieldAccessibleIfNeeded(Field field) {
        if (field != null && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }
}
