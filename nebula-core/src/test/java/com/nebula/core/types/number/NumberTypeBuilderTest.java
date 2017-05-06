package com.nebula.core.types.number;

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
}
