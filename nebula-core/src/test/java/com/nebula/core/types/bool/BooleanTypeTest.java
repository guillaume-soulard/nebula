package com.nebula.core.types.bool;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class BooleanTypeTest {

	@Test
	void getMinRange_should_return_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
	void generateObject_should_return_non_null_object() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void generateObject_should_return_false_when_index_is_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.FALSE);
	}

	@Test
	void generateObject_should_return_true_when_index_is_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        GeneratedObject result = booleanType.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.TRUE);
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        catchException(booleanType).generateObject(Collections.emptyList(), -1L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_greater_than_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        catchException(booleanType).generateObject(Collections.emptyList(), 2L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_null() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
        catchException(booleanType).generateObject(Collections.emptyList(), null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
