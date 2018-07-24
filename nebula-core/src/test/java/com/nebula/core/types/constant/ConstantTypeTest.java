package com.nebula.core.types.constant;

import com.nebula.core.GeneratedObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ConstantTypeTest {

	@Test
	void new_ConstantType_should_set_value_test() {

		// GIVEN
		Object value = "test";
		ConstantType constantType;

		// WHEN
		constantType = new ConstantType(value);

		// THEN
		assertThat(constantType).hasFieldOrPropertyWithValue("value", value);
	}

	@Test
	void getMinRange_should_return_0() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
		Long result = constantType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_0() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
		Long result = constantType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
		GeneratedObject result = constantType.generateObject(0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void generateObject_should_return_test_as_value() {

		// GIVEN
		String value = "test";
		ConstantType constantType = new ConstantType(value);

		// WHEN
		GeneratedObject result = constantType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(value);
	}

	@Test
	void generateObject_should_return_0() {

		// GIVEN
		BigDecimal value = BigDecimal.ZERO;
		ConstantType constantType = new ConstantType(value);

		// WHEN
		GeneratedObject result = constantType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(value);
	}

	@Test
	void generateObject_should_return_same_value_for_index_0_and_1() {

		// GIVEN
		String value = "test";
		ConstantType constantType = new ConstantType(value);
		GeneratedObject resultAtIndex0 = constantType.generateObject(0L);

		// WHEN
		GeneratedObject result = constantType.generateObject(1L);

		// THEN
		assertThat(result.getObject()).isEqualTo(resultAtIndex0.getObject());
	}
}
