package com.nebula.object.fieldsetter;

import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberFieldSetterTest extends AbstarctFieldSetterTest {

    private BigDecimal privateField;
    private int intField;
    private double doubleField;
    private float floatField;
    private long longField;

    private Field getPrivateField() throws Exception {
        return getFieldByName("privateField");
    }

    private Field getIntField() throws Exception {
        return getFieldByName("intField");
    }

    private Field getLongField() throws Exception {
        return getFieldByName("longField");
    }

    private Field getDoubleField() throws Exception {
        return getFieldByName("doubleField");
    }

    private Field getFloatField() throws Exception {
        return getFieldByName("floatField");
    }

    @Test
    public void setValueToObjectForField_should_set_value_on_private_field() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getPrivateField();
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.privateField).isEqualTo(value);
    }

    @Test
    public void setValueToObjectForField_should_do_nothing_when_object_is_null() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getPrivateField();
        BigDecimal value = BigDecimal.ONE;
        AbstarctFieldSetterTest object = null;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object).isNull();
    }

    @Test
    public void setValueToObjectForField_should_set_null() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getPrivateField();
        BigDecimal value = null;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.privateField).isNull();
    }

    @Test
    public void setValueToObjectForField_should_set_1_in_int_field() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getIntField();
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.intField).isEqualTo(1);
    }

    @Test
    public void setValueToObjectForField_should_set_1_in_long_field() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getLongField();
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.longField).isEqualTo(1l);
    }

    @Test
    public void setValueToObjectForField_should_set_1_in_double_field() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getDoubleField();
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.doubleField).isEqualTo(1.0d);
    }

    @Test
    public void setValueToObjectForField_should_set_1_in_float_field() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = getFloatField();
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.floatField).isEqualTo(1.0f);
    }

    @Test
    public void setValueToObjectForField_should_do_nothing_when_field_is_null() throws Exception {

        // GIVEN
        NumberFieldSetter fieldSetter = new NumberFieldSetter();
        Field field = null;
        BigDecimal value = BigDecimal.ONE;
        NumberFieldSetterTest object = this;

        // WHEN
        fieldSetter.setValueToObjectForField(object, field, value);

        // THEN
        assertThat(object.privateField).isNull();
    }
}
