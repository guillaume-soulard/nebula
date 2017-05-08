package com.nebula.core.types.date;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class DateTimeTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DateTimeType() {

		// GIVEN
		TypeBuilder builder = new DateTimeTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(DateTimeType.class);
	}

	@Test
	public void build_should_set_default_values_in_DateTimeBuilder() {

		// GIVEN
		TypeBuilder builder = new DateTimeTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", new DateTime(Long.MIN_VALUE))
				.hasFieldOrPropertyWithValue("range.max", new DateTime(Long.MAX_VALUE))
				.hasFieldOrPropertyWithValue("interval", DateTimeTypeIntervals.DAY);
	}

	@Test
	public void withMin_should_set_min_date_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeTypeBuilder builder = new DateTimeTypeBuilder();
		DateTime min = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		Type result = builder.withMin(min).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", min);
	}

	@Test
	public void withMax_should_set_max_date_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeTypeBuilder builder = new DateTimeTypeBuilder();
		DateTime max = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		Type result = builder.withMax(max).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", max);
	}

	@Test
	public void withINterval_should_set_ths_interval_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeTypeBuilder builder = new DateTimeTypeBuilder();
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MONTH;

		// WHEN
		Type result = builder.withInterval(interval).build();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("interval", interval);
	}
}
