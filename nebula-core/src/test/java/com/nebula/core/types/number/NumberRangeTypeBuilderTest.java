package com.nebula.core.types.number;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberRangeTypeBuilderTest {

	@Test
    @DisplayName("build should return a new instance of DoubleType with defaults")
	void build_should_return_a_new_instance_of_DoubleType_with_defaults() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(NumberRangeType.class);
	}

	@Test
    @DisplayName("build should set 0 as default precision in double type object")
	void build_should_set_0_as_default_precision_in_double_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 0);
	}

	@Test
    @DisplayName("build should set precision in double type object")
	void build_should_set_precision_in_double_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withPrecision(1).build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 1);
	}

	@Test
    @DisplayName("build should set min in number type object")
	void build_should_set_min_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withMin(BigDecimal.valueOf(10)).build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.TEN);
	}

	@Test
    @DisplayName("build should set Integer MIN VALUE as default min in number type object")
	void build_should_set_Integer_MIN_VALUE_as_default_min_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.valueOf(Integer.MIN_VALUE));
	}

	@Test
    @DisplayName("build should set Integer MAX VALUE as default max in number type object")
	void build_should_set_Integer_MAX_VALUE_as_default_max_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(Integer.MAX_VALUE));
	}

	@Test
    @DisplayName("build should set max in number type object")
	void build_should_set_max_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withMax(BigDecimal.valueOf(456)).build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(456));
	}

	@Test
    @DisplayName("withMin and withMax should not throw exception for the same value")
	void withMin_and_withMax_should_not_throw_exception_for_the_same_value() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();
		BigDecimal value = BigDecimal.valueOf(456);

		// WHEN
		builder.withMin(value).withMax(value);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("min", value).hasFieldOrPropertyWithValue("max", value);
	}

	@Test
    @DisplayName("withMin should throw exception when min is greater than max")
	void withMin_should_throw_exception_when_min_is_greater_than_max() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();
		BigDecimal min = BigDecimal.valueOf(456);
		BigDecimal max = BigDecimal.ZERO;

		// WHEN

		// THEN
        assertThatThrownBy(() -> builder.withMin(min).withMax(max).build(ModelBuilder.newEmptyModel().build()))
                .isInstanceOf(NebulaException.class)
                .hasMessage("max must be greater than min");
	}

	@Test
    @DisplayName("withPrecision should throw exception when precision is negative")
	void withPrecision_should_throw_exception_when_precision_is_negative() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN

		// THEN
        assertThatThrownBy(() -> builder.withPrecision(-1))
                .isInstanceOf(NebulaException.class)
                .hasMessage("precision is negative");
	}

	@Test
    @DisplayName("builder should have access to precision and min")
	void builder_should_have_access_to_precision_and_min() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		builder.withPrecision(1).withMin(BigDecimal.ZERO);

		// THEN
	}
}
