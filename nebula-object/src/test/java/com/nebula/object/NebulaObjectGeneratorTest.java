package com.nebula.object;

import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NebulaObjectGeneratorTest {

    @Test
    public void getAtIndex_should_return_a_non_null_object_with_expected_generated_fields() {

        // GIVEN
        NebulaObjectGenerator<ConstantIntFieldsTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(ConstantIntFieldsTestClass.class);
        long index = 0l;

        // WHEN
        ConstantIntFieldsTestClass result = nebulaObjectGenerator.getObjectAtIndex(index);

        // THEN
        assertThat(result.getPrivateIntField()).isEqualTo(10);
        assertThat(result.publicIntField).isEqualTo(20);
        assertThat(result.protectedIntField).isEqualTo(30);
        assertThat(result.publicIntFieldNotGenerated).isEqualTo(0);
    }

    @Test
    public void getAtIndex_should_return_a_different_object_on_different_index() {

        // GIVEN
        NebulaObjectGenerator<RandomIntFieldsTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(RandomIntFieldsTestClass.class);
        RandomIntFieldsTestClass resultAtFirstIndex = nebulaObjectGenerator.getObjectAtIndex(0l);

        // WHEN
        RandomIntFieldsTestClass result = nebulaObjectGenerator.getObjectAtIndex(1l);

        // THEN
        assertThat(result.getPrivateIntField()).isNotEqualTo(resultAtFirstIndex.getPrivateIntField());
    }

    @Test
    public void getAtIndex_should_return_an_object_with_non_null_string() {

        // GIVEN
        NebulaObjectGenerator<ConstantStringFieldTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(ConstantStringFieldTestClass.class);

        // WHEN
        ConstantStringFieldTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        assertThat(result).hasFieldOrPropertyWithValue("field", "aaaaaaaaaa");
    }

    @Test
    public void getAtIndex_should_return_an_object_expected_values_for_number_range() {

        // GIVEN
        NebulaObjectGenerator<NumberRangeFieldsTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(NumberRangeFieldsTestClass.class);

        // WHEN
        NumberRangeFieldsTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        assertThat(result).hasFieldOrPropertyWithValue("intField", 10)
                .hasFieldOrPropertyWithValue("longField", 10l)
                .hasFieldOrPropertyWithValue("doubleField", 10d)
                .hasFieldOrPropertyWithValue("floatField", 10f)
                .hasFieldOrPropertyWithValue("intObjectField", 10)
                .hasFieldOrPropertyWithValue("longObjectField", 10l)
                .hasFieldOrPropertyWithValue("doubleObjectField", 10d)
                .hasFieldOrPropertyWithValue("floatObjectField", 10f)
                .hasFieldOrPropertyWithValue("bigDecimalField", BigDecimal.TEN);
    }

    @Test
    public void getAtIndex_should_return_an_object_expected_values_for_number_list() {

        // GIVEN
        NebulaObjectGenerator<NumberListFieldsTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(NumberListFieldsTestClass.class);

        // WHEN
        NumberListFieldsTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        assertThat(result).hasFieldOrPropertyWithValue("intField", 20)
                .hasFieldOrPropertyWithValue("longField", 20l)
                .hasFieldOrPropertyWithValue("doubleField", 20d)
                .hasFieldOrPropertyWithValue("floatField", 20f)
                .hasFieldOrPropertyWithValue("intObjectField", 20)
                .hasFieldOrPropertyWithValue("longObjectField", 20l)
                .hasFieldOrPropertyWithValue("doubleObjectField", 20d)
                .hasFieldOrPropertyWithValue("floatObjectField", 20f)
                .hasFieldOrPropertyWithValue("bigDecimalField", BigDecimal.TEN.add(BigDecimal.TEN));
    }

    @Test
    public void getAtIndex_should_return_an_object_expected_date_field_for_date_range() {

        // GIVEN
        NebulaObjectGenerator<DateRangeFieldTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(DateRangeFieldTestClass.class);

        // WHEN
        DateRangeFieldTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        DateTime expectedDate = new DateTime(2017, 1, 1, 0, 0);
        assertThat(result).hasFieldOrPropertyWithValue("dateField", expectedDate.toDate())
                .hasFieldOrPropertyWithValue("dateTimeField", expectedDate);
    }

    @Test
    public void getAtIndex_should_return_an_object_expected_date_field_for_date_list() {

        // GIVEN
        NebulaObjectGenerator<DateListFieldTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(DateListFieldTestClass.class);

        // WHEN
        DateListFieldTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        DateTime expectedDate = new DateTime(2017, 1, 1, 0, 0);
        assertThat(result).hasFieldOrPropertyWithValue("dateField", expectedDate.toDate())
                .hasFieldOrPropertyWithValue("dateTimeField", expectedDate);
    }

    @Test
    public void getAtIndex_should_return_an_object_with_boolean_filled() {

        // GIVEN
        NebulaObjectGenerator<BooleanFieldTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(BooleanFieldTestClass.class);

        // WHEN
        BooleanFieldTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        assertThat(result.booleanField).isIn(true, false);
    }

    @Test
    public void getAtIndex_should_return_an_object_with_entity_filled() {

        // GIVEN
        NebulaObjectGenerator<EntityFieldTestClass> nebulaObjectGenerator = new NebulaObjectGenerator<>(EntityFieldTestClass.class);

        // WHEN
        EntityFieldTestClass result = nebulaObjectGenerator.getObjectAtIndex(0l);

        // THEN
        assertThat(result.entityTest).isNotNull();
        assertThat(result.entityTest.booleanField).isIn(true, false);
    }
}
