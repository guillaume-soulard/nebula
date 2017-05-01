package com.nebula.core.types.number;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.NebulaTypes;
import com.nebula.core.types.Range;

public class LongTypeTest {

	@Test
	public void integer_should_return_a_new_instance_of_IntegerType() throws NebulaException {
		// GIVEN
		LongType type = null;

		// WHEN
		type = (LongType) NebulaTypes.integer().build();

		// THEN
		assertThat(type).isNotNull();
	}

	@Test
	public void integer_should_return_IntegerType_with_IntegerMin_and_IntegerMax_as_range() throws NebulaException {
		// GIVEN
		LongType type = null;

		// WHEN
		type = (LongType) NebulaTypes.integer().build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(Long.MIN_VALUE);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void integerRange_should_return_IntegerType_with_given_min_and_max_value() throws NebulaException {
		// GIVEN
		LongType type = null;

		// WHEN
		type = (LongType) NebulaTypes.integer().withMin(10).build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(10l);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void integer_should_return_IntegerType_with_min_0_and_max_Integer_max() throws NebulaException {
		// GIVEN
		LongType type = null;

		// WHEN
		type = (LongType) NebulaTypes.integer().withMin(0).build();

		// THEN
		assertThat(type.getMinRange()).isEqualTo(0l);
		assertThat(type.getMaxRange()).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void getMinRange_should_return_min_integer_value() throws NebulaException {
		// GIVEN
		LongType type = (LongType) NebulaTypes.integer().build();

		// WHEN
		Object result = type.getMinRange();

		// THEN
		assertThat(result).isEqualTo(Long.MIN_VALUE);
	}

	@Test
	public void getMaxRange_should_return_max_integer_value() throws NebulaException {
		// GIVEN
		LongType type = (LongType) NebulaTypes.integer().build();

		// WHEN
		Object result = type.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(Long.MAX_VALUE);
	}

	@Test
	public void generateObject_should_return_10_when_object_index_0_is_passed() throws NebulaException {
		// GIVEN
		LongType type = (LongType) NebulaTypes.integer().withMin(10).build();

		// WHEN
		GeneratedObject result = type.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isEqualTo(10l);
	}

	@Test
	public void generateObject_should_return_35_when_object_index_12_is_passed() throws NebulaException {
		// GIVEN
		LongType type = (LongType) NebulaTypes.integer().withMin(23).build();

		// WHEN
		GeneratedObject result = type.generateObject(12l);

		// THEN
		assertThat(result.getObject()).isEqualTo(35l);
	}

	@Test
	public void should_throw_exception_when_requested_index_is_out_of_range() throws NebulaException {

		// GIVEN
		Range<Long> range = new Range<Long>(0l, 1l);
		LongType LongType = new LongType(range);

		// WHEN
		catchException(LongType).generateObject(10l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
