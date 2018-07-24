package com.nebula.core.types.number;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class NumberRangeTypeBuilderTest {

	@Test
	void build_should_return_a_new_instance_of_DoubleType_with_defaults() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).isInstanceOf(NumberRangeType.class);
	}

	@Test
	void build_should_set_0_as_default_precision_in_double_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 0);
	}

	@Test
	void build_should_set_precision_in_double_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withPrecision(1).build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 1);
	}

	@Test
	void build_should_set_min_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withMin(BigDecimal.valueOf(10)).build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.TEN);
	}

	@Test
	void build_should_set_Integer_MIN_VALUE_as_default_min_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.valueOf(Integer.MIN_VALUE));
	}

	@Test
	void build_should_set_Integer_MAX_VALUE_as_default_max_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(Integer.MAX_VALUE));
	}

	@Test
	void build_should_set_max_in_number_type_object() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		Type result = builder.withMax(BigDecimal.valueOf(456)).build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(456));
	}

	@Test
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
	void withMin_should_throw_exception_when_min_is_greater_than_max() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();
		BigDecimal min = BigDecimal.valueOf(456);
		BigDecimal max = BigDecimal.ZERO;

		// WHEN
		catchException(builder.withMin(min).withMax(max)).build(ModelBuilder.newModel().build());

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("max must be greater than min");
	}

	@Test
	void withPrecision_should_throw_exception_when_precision_is_negative() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		catchException(builder).withPrecision(-1);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("precision is negative");
	}

	@Test
	void builder_should_have_access_to_precision_and_min() {

		// GIVEN
		NumberRangeTypeBuilder builder = new NumberRangeTypeBuilder();

		// WHEN
		builder.withPrecision(1).withMin(BigDecimal.ZERO);

		// THEN
	}
}
