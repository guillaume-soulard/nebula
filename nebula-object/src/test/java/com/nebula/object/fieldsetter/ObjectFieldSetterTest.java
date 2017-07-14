package com.nebula.object.fieldsetter;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectFieldSetterTest extends AbstarctFieldSetterTest {

    private String stringField;

    private Field getStringField() throws Exception {
        return getFieldByName("stringField");
    }

    @Test
    public void setValueToObjectForField_should_set_value_in_field() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new ObjectFieldSetter();
        ObjectFieldSetterTest object = this;
        Field field = getStringField();
        Object value = "test";

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.stringField).isEqualTo(value);
    }

    @Test
    public void setValueToObjectForField_should_set_null_in_field() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new ObjectFieldSetter();
        ObjectFieldSetterTest object = this;
        Field field = getStringField();

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, null);

        // THEN
        assertThat(object.stringField).isNull();
    }
}