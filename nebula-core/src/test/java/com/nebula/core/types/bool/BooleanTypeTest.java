package com.nebula.core.types.bool;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BooleanTypeTest {

	@Test
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 1")
	void getMaxRange_should_return_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
    @DisplayName("generateObject should return non null object")
	void generateObject_should_return_non_null_object() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return false when index is 0")
	void generateObject_should_return_false_when_index_is_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.FALSE);
	}

	@Test
    @DisplayName("generateObject should return true when index is 1")
	void generateObject_should_return_true_when_index_is_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.TRUE);
	}

	@Test
    @DisplayName("generateObject should throw exception when index is negative")
	void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN

		// THEN
        assertThatThrownBy(() -> booleanType.generateObject(Collections.emptyList(), -1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("generateObject should throw exception when index is greater than 1")
	void generateObject_should_throw_exception_when_index_is_greater_than_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN

		// THEN
        assertThatThrownBy(() -> booleanType.generateObject(Collections.emptyList(), 2L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("generateObject should throw exception when index is null")
	void generateObject_should_throw_exception_when_index_is_null() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN

		// THEN
        assertThatThrownBy(() -> booleanType.generateObject(Collections.emptyList(), null))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
