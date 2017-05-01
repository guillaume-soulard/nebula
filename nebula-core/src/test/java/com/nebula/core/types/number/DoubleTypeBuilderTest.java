package com.nebula.core.types.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public class DoubleTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DoubleType_with_defaults() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(DoubleType.class);
	}

	@Test
	public void build_should_set_0_as_default_precision_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 0);
	}

	@Test
	public void build_should_set_precision_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.withPrecision(1).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("precision", 1);
	}

	@Test
	public void build_should_set_min_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.withMin(10).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", 10.0);
	}

	@Test
	public void build_should_set_Double_MIN_VALUE_as_default_min_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", Double.MIN_VALUE);
	}

	@Test
	public void build_should_set_Double_MAX_VALUE_as_default_max_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", Double.MAX_VALUE);
	}

	@Test
	public void build_should_set_max_in_double_type_object() throws NebulaException {

		// GIVEN
		DoubleTypeBuilder builder = new DoubleTypeBuilder();

		// WHEN
		Type result = builder.withMax(456).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", 456.0);
	}
}
