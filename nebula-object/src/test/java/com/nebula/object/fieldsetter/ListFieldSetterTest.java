package com.nebula.object.fieldsetter;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListFieldSetterTest extends AbstarctFieldSetterTest {

    private List<String> listField;
    private String[] arrayField;

    private Field getListField() throws Exception {
        return getFieldByName("listField");
    }

    private Field getArrayField() throws Exception {
        return getFieldByName("arrayField");
    }

    @Test
    public void setValueToObjectForField_should_set_value_in_list_field() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new ListFieldSetter();
        ListFieldSetterTest object = this;
        Field field = getListField();
        Object value = Arrays.asList("string", "string");

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(this.listField).isEqualTo(value);
    }

    @Test
    public void setValueToObjectForField_should_set_value_in_list_array() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new ListFieldSetter();
        ListFieldSetterTest object = this;
        Field field = getArrayField();
        List<String> value = Arrays.asList("string", "string");

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(this.arrayField).isEqualTo(value.toArray());
    }
}