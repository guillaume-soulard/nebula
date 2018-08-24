package com.nebula.core.types.number;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class NumberRangeTypeTest {

	@Test
	void generateObject_should_return_0_dot_5_when_min_0_and_max_1_and_precision_1_for_index_5() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        GeneratedObject result = doubleType.generateObject(Collections.emptyList(), 5L);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.valueOf(0.5));
	}

	@Test
	void generateObject_should_return_0_dot_05_when_min_0_and_max_10_and_precision_2_for_index_5() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0d), BigDecimal.valueOf(10d));
		int precision = 2;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        GeneratedObject result = doubleType.generateObject(Collections.emptyList(), 5L);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.valueOf(0.05));
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_out_of_range() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        catchException(doubleType).generateObject(Collections.emptyList(), 100L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	void getMinRange_should_return_0() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_0() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
		int precision = 0;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_1() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 0;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
	void getMaxRange_should_return_1000000() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(10), BigDecimal.valueOf(20d));
		int precision = 5;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1000000);
	}

	@Test
	void getMaxRange_should_return_5000() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(12), BigDecimal.valueOf(17d));
		int precision = 3;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(5000L);
	}

	@Test
	void generateObject_should_throw_exception_when_negative_index_is_passed() {

		// GIVEN
		Range<BigDecimal> range = new Range<>(BigDecimal.valueOf(0d), BigDecimal.valueOf(10d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        catchException(doubleType).generateObject(Collections.emptyList(), -1L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
