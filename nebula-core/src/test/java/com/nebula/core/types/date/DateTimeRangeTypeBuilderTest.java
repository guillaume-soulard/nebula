package com.nebula.core.types.date;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeRangeTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_DateTimeType() {

		// GIVEN
		TypeBuilder builder = new DateTimeRangeTypeBuilder();

		// WHEN
		Type result = builder.build(new ModelBuilder().build());

		// THEN
		assertThat(result).isInstanceOf(DateTimeRangeType.class);
	}

	@Test
	public void build_should_set_default_values_in_DateTimeBuilder() {

		// GIVEN
		TypeBuilder builder = new DateTimeRangeTypeBuilder();

		// WHEN
		Type result = builder.build(new ModelBuilder().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", new DateTime(0l))
				.hasFieldOrPropertyWithValue("range.max", new DateTime(9999, 12, 31, 0, 0))
				.hasFieldOrPropertyWithValue("interval", DateTimeTypeIntervals.DAY);
	}

	@Test
	public void withMin_should_set_min_date_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeRangeTypeBuilder builder = new DateTimeRangeTypeBuilder();
		DateTime min = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		Type result = builder.withMin(min).build(new ModelBuilder().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.min", min);
	}

	@Test
	public void withMax_should_set_max_date_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeRangeTypeBuilder builder = new DateTimeRangeTypeBuilder();
		DateTime max = new DateTime(2017, 1, 1, 0, 0);

		// WHEN
		Type result = builder.withMax(max).build(new ModelBuilder().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("range.max", max);
	}

	@Test
	public void withINterval_should_set_ths_interval_in_new_instance_of_DateTimeType() {

		// GIVEN
		DateTimeRangeTypeBuilder builder = new DateTimeRangeTypeBuilder();
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MONTH;

		// WHEN
		Type result = builder.withInterval(interval).build(new ModelBuilder().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("interval", interval);
	}
}
