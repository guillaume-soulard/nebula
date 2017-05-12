package com.nebula.core.types.bool;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;

public class BooleanTypeTest {

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		Long result = booleanType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1l);
	}

	@Test
	public void generateObject_should_return_non_null_object() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		GeneratedObject result = booleanType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_false_when_index_is_0() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		GeneratedObject result = booleanType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.FALSE);
	}

	@Test
	public void generateObject_should_return_true_when_index_is_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		GeneratedObject result = booleanType.generateObject(1l);

		// THEN
		assertThat(result.getObject()).isEqualTo(Boolean.TRUE);
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		catchException(booleanType).generateObject(-1l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_greater_than_1() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		catchException(booleanType).generateObject(2l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_null() {

		// GIVEN
		BooleanType booleanType = new BooleanType();

		// WHEN
		catchException(booleanType).generateObject(null);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}