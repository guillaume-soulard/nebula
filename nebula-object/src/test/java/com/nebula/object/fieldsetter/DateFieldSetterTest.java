package com.nebula.object.fieldsetter;

import org.joda.time.DateTime;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class DateFieldSetterTest extends AbstarctFieldSetterTest {

    private Date dateField;
    private DateTime dateTimeField;

    private Field getDateField() throws Exception {
        return getFieldByName("dateField");
    }

    private Field getDateTimeField() throws Exception {
        return getFieldByName("dateTimeField");
    }

    @Test
    public void setValueToObjectForField_should_set_value_in_field() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new DateFieldSetter();
        DateFieldSetterTest object = this;
        Field field = getDateTimeField();
        Object value = new DateTime(2017, 1, 1, 0, 0);

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.dateTimeField).isEqualTo(value);
    }

    @Test
    public void setValueToObjectForField_should_set_null_in_field_when_value_is_null() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new DateFieldSetter();
        DateFieldSetterTest object = this;
        Field field = getDateTimeField();
        Object value = null;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.dateTimeField).isNull();
    }

    @Test
    public void setValueToObjectForField_should_do_nothing_when_object_is_null() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new DateFieldSetter();
        DateFieldSetterTest object = null;
        Field field = getDateTimeField();
        Object value = new DateTime(2017, 1, 1, 0, 0);

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object).isNull();
    }

    @Test
    public void setValueToObjectForField_should_do_nothing_when_field_is_null() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new DateFieldSetter();
        DateFieldSetterTest object = this;
        Field field = null;
        Object value = new DateTime(2017, 1, 1, 0, 0);

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.dateTimeField).isNull();
    }

    @Test
    public void setValueToObjectForField_should_set_date_field() throws Exception {

        // GIVEN
        FieldSetter fieldSetter = new DateFieldSetter();
        DateFieldSetterTest object = this;
        Field field = getDateField();
        DateTime value = new DateTime(2017, 1, 1, 0, 0);

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.dateField).isEqualTo(value.toDate());
    }
}