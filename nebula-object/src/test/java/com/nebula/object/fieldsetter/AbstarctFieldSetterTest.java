package com.nebula.object.fieldsetter;

import java.lang.reflect.Field;


public class AbstarctFieldSetterTest {

    protected Field getFieldByName(String fieldName) throws Exception {
        return this.getClass().getDeclaredField(fieldName);
    }
}
