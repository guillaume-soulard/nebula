package com.nebula.core.types.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.NebulaTypes;

public class NumberTypeTest {

	@Test
	public void integer_should_return_a_new_instance_of_IntegerType() throws NebulaException {
		// GIVEN
		NumberType type = null;

		// WHEN
		type = (NumberType) NebulaTypes.number().build();

		// THEN
		assertThat(type).isNotNull();
	}

	@Test
	public void integer_should_return_IntegerType_with_IntegerMin_and_IntegerMax_as_range() throws NebulaException {
		// GIVEN
		NumberType type = null;

		// WHEN
		type = (NumberType) NebulaTypes.number().build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(Long.MIN_VALUE);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void integerRange_should_return_IntegerType_with_given_min_and_max_value() throws NebulaException {
		// GIVEN
		NumberType type = null;

		// WHEN
		type = (NumberType) NebulaTypes.number().withMin(10).build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(10l);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void integer_should_return_IntegerType_with_min_0_and_max_Integer_max() throws NebulaException {
		// GIVEN
		NumberType type = null;

		// WHEN
		type = (NumberType) NebulaTypes.number().withMin(0).build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(0l);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void getMinRange_should_return_min_integer_value() throws NebulaException {
		// GIVEN
		NumberType type = (NumberType) NebulaTypes.number().build();

		// WHEN
		Object result = type.getMinRange();

		// THEN
		assertThat(result).isEqualTo(Long.MIN_VALUE);
	}

	@Test
	public void getMaxRange_should_return_max_integer_value() throws NebulaException {
		// GIVEN
		NumberType type = (NumberType) NebulaTypes.number().build();

		// WHEN
		Object result = type.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void generateObject_should_return_10_when_object_index_0_is_passed() throws NebulaException {
		// GIVEN
		NumberType type = (NumberType) NebulaTypes.number().withMin(10).build();

		// WHEN
		GeneratedObject result = type.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isEqualTo(10l);
	}

	@Test
	public void generateObject_should_return_35_when_object_index_12_is_passed() throws NebulaException {
		// GIVEN
		NumberType type = (NumberType) NebulaTypes.number().withMin(23).build();

		// WHEN
		GeneratedObject result = type.generateObject(12l);

		// THEN
		assertThat(result.getObject()).isEqualTo(35l);
	}
}
