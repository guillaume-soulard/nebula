package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeYearStrategyTest {

	@Test
	public void getByDateAndIndexAndInterval_should_return_the_initial_date_for_index_0() {

		// GIVEN
		DateTimeStrategy adder = new DateTimeYearStrategy();
		DateTime initialDate = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		Long index = 0L;

		// WHEN
		DateTime result = adder.getByDateAndIndexAndInterval(initialDate, index);

		// THEN
		assertThat(result).isEqualTo(initialDate);
	}

	@Test
	public void getByDateAndIndexAndInterval_should_return_the_initial_plus_1_milisecond_date_for_index_1() {

		// GIVEN
		DateTimeStrategy adder = new DateTimeYearStrategy();
		DateTime initialDate = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		Long index = 1L;

		// WHEN
		DateTime result = adder.getByDateAndIndexAndInterval(initialDate, index);

		// THEN
		assertThat(result).isEqualTo(new DateTime(2018, 1, 1, 0, 0, 0, 0));
	}

	@Test
	public void getMaxIndex_should_return_10() {

		// GIVEN
		DateTimeStrategy strategy = new DateTimeYearStrategy();
		DateTime min = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime max = new DateTime(2027, 1, 1, 0, 0, 0, 0);

		// WHEN
		Long result = strategy.getMaxIndex(min, max);

		// THEN
		assertThat(result).isEqualTo(10L);
	}

	@Test
	public void getMaxIndex_should_return_1() {

		// GIVEN
		DateTimeStrategy strategy = new DateTimeYearStrategy();
		DateTime min = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime max = new DateTime(2018, 1, 1, 0, 0, 0, 0);

		// WHEN
		Long result = strategy.getMaxIndex(min, max);

		// THEN
		assertThat(result).isEqualTo(1L);
	}
}