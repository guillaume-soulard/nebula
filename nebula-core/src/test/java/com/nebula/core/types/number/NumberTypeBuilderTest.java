package com.nebula.core.types.number;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public class NumberTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DoubleType_with_defaults() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(NumberType.class);
	}

	@Test
	public void build_should_set_0_as_default_precision_in_double_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 0);
	}

	@Test
	public void build_should_set_precision_in_double_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.withPrecision(1).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 1);
	}

	@Test
	public void build_should_set_min_in_number_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.withMin(BigDecimal.valueOf(10)).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.TEN);
	}

	@Test
	public void build_should_set_Long_MIN_VALUE_as_default_min_in_number_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", BigDecimal.valueOf(Long.MIN_VALUE));
	}

	@Test
	public void build_should_set_Long_MAX_VALUE_as_default_max_in_number_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(Long.MAX_VALUE));
	}

	@Test
	public void build_should_set_max_in_number_type_object() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		Type result = builder.withMax(BigDecimal.valueOf(456)).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", BigDecimal.valueOf(456));
	}

	@Test
	public void withMin_and_withMax_should_not_throw_exception_for_the_same_value() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();
		BigDecimal value = BigDecimal.valueOf(456);

		// WHEN
		builder.withMin(value).withMax(value);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("min", value).hasFieldOrPropertyWithValue("max", value);
	}

	@Test
	public void withMin_should_throw_exception_when_min_is_greater_than_max() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();
		BigDecimal min = BigDecimal.valueOf(456);
		BigDecimal max = BigDecimal.ZERO;

		// WHEN
		catchException(builder.withMin(min).withMax(max)).build();

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("max must be greater than min");
	}

	@Test
	public void withPrecision_should_throw_exception_when_precision_is_negative() throws NebulaException {

		// GIVEN
		NumberTypeBuilder builder = new NumberTypeBuilder();

		// WHEN
		catchException(builder).withPrecision(-1);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("precision is negative");
	}
}
