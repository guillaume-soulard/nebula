package com.nebula.core.types.number;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public class LongTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_IntegerType() throws NebulaException {
		// GIVEN
		LongTypeBuilder builder = new LongTypeBuilder();

		// WHEN
		Type type = builder.build();

		// THEN
		assertThat(type).isInstanceOf(LongType.class);
	}

	@Test
	public void build_should_set_Long_MIN_VALUE_as_default_min() throws NebulaException {
		// GIVEN
		LongTypeBuilder builder = new LongTypeBuilder();

		// WHEN
		Type type = builder.build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.min", Long.MIN_VALUE);
	}

	@Test
	public void build_should_set_Long_MAX_VALUE_as_default_max() throws NebulaException {
		// GIVEN
		LongTypeBuilder builder = new LongTypeBuilder();

		// WHEN
		Type type = builder.build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.max", Long.MAX_VALUE);
	}

	@Test
	public void build_should_return_IntegerType_with_min_as_Integer_min_and_max_as_Integer_max()
			throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();

		// WHEN
		LongType type = (LongType) builder.build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.min", Long.MIN_VALUE)
				.hasFieldOrPropertyWithValue("range.max", Long.MAX_VALUE);
	}

	@Test
	public void withMin_should_set_5_as_min_value() throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();
		long min = 5l;

		// WHEN
		LongType type = (LongType) builder.withMin(min).build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.min", min).hasFieldOrPropertyWithValue("range.max",
				Long.MAX_VALUE);
	}

	@Test
	public void withMin_should_set_5_as_max_value() throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();
		long max = 5;

		// WHEN
		LongType type = (LongType) builder.withMax(max).build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.min", Long.MIN_VALUE)
				.hasFieldOrPropertyWithValue("range.max", max);
	}

	@Test
	public void withMax_should_throw_exception_when_value_is_less_than_min() throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();
		int min = 5;
		int max = -5;
		builder.withMin(min);

		// WHEN
		catchException(builder).withMax(max);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("max should be greater than min");
	}

	@Test
	public void withMin_should_throw_exception_when_value_is_greater_than_max() throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();
		int min = 5;
		int max = -5;
		builder.withMax(max);

		// WHEN
		catchException(builder).withMin(min);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("min should be less than max");
	}

	@Test
	public void build_should_return_custom_range_with_minus_5_as_min_and_5_as_max() throws NebulaException {

		LongTypeBuilder builder = new LongTypeBuilder();
		long min = -5;
		long max = 5;

		// WHEN
		LongType type = (LongType) builder.withMin(min).withMax(max).build();

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("range.min", min).hasFieldOrPropertyWithValue("range.max", max);
	}
}
