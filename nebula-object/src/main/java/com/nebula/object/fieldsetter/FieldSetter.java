package com.nebula.object.fieldsetter;

import java.lang.reflect.Field;

public interface FieldSetter {

    void setValueToObjectForField(Object object, Field field, Object value);
}
