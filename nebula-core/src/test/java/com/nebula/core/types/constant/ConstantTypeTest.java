package com.nebula.core.types.constant;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ConstantTypeTest {

	@Test
    @DisplayName("new ConstantType should set value test")
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
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
		Long result = constantType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 0")
	void getMaxRange_should_return_0() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
		Long result = constantType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("generateObject should return a non null object")
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		ConstantType constantType = new ConstantType(null);

		// WHEN
        GeneratedObject result = constantType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return test as value")
	void generateObject_should_return_test_as_value() {

		// GIVEN
		String value = "test";
		ConstantType constantType = new ConstantType(value);

		// WHEN
        GeneratedObject result = constantType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(value);
	}

	@Test
    @DisplayName("generateObject should return 0")
	void generateObject_should_return_0() {

		// GIVEN
		BigDecimal value = BigDecimal.ZERO;
		ConstantType constantType = new ConstantType(value);

		// WHEN
        GeneratedObject result = constantType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(value);
	}

	@Test
    @DisplayName("generateObject should return same value for index 0 and 1")
	void generateObject_should_return_same_value_for_index_0_and_1() {

		// GIVEN
		String value = "test";
		ConstantType constantType = new ConstantType(value);
        GeneratedObject resultAtIndex0 = constantType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// WHEN
        GeneratedObject result = constantType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L);

		// THEN
		assertThat(result.getObject()).isEqualTo(resultAtIndex0.getObject());
	}
}
