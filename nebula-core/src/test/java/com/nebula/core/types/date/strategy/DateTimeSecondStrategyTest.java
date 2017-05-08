package com.nebula.core.types.date.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.nebula.core.NebulaException;

public class DateTimeSecondStrategyTest {

	@Test
	public void getByDateAndIndexAndInterval_should_return_the_initial_date_for_index_0() {

		// GIVEN
		DateTimeStrategy adder = new DateTimeSecondStrategy();
		DateTime initialDate = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		Long index = 0l;

		// WHEN
		DateTime result = adder.getByDateAndIndexAndInterval(initialDate, index);

		// THEN
		assertThat(result).isEqualTo(initialDate);
	}

	@Test
	public void getByDateAndIndexAndInterval_should_return_the_initial_plus_1_milisecond_date_for_index_1() {

		// GIVEN
		DateTimeStrategy adder = new DateTimeSecondStrategy();
		DateTime initialDate = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		Long index = 1l;

		// WHEN
		DateTime result = adder.getByDateAndIndexAndInterval(initialDate, index);

		// THEN
		assertThat(result).isEqualTo(new DateTime(2017, 1, 1, 0, 0, 1, 0));
	}

	@Test
	public void getMaxIndex_should_return_20() {

		// GIVEN
		DateTimeStrategy strategy = new DateTimeSecondStrategy();
		DateTime min = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime max = new DateTime(2017, 1, 1, 0, 0, 20, 0);

		// WHEN
		Long result = strategy.getMaxIndex(min, max);

		// THEN
		assertThat(result).isEqualTo(20l);
	}

	@Test
	public void getMaxIndex_should_return_1() {

		// GIVEN
		DateTimeStrategy strategy = new DateTimeSecondStrategy();
		DateTime min = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime max = new DateTime(2017, 1, 1, 0, 0, 1, 0);

		// WHEN
		Long result = strategy.getMaxIndex(min, max);

		// THEN
		assertThat(result).isEqualTo(1l);
	}
}