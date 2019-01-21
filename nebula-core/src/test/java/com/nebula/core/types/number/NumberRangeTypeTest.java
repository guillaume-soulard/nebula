package com.nebula.core.types.number;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberRangeTypeTest {

	@Test
    @DisplayName("generateObject should return 0 dot 5 when min 0 and max 1 and precision 1 for index 5")
	void generateObject_should_return_0_dot_5_when_min_0_and_max_1_and_precision_1_for_index_5() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        GeneratedObject result = doubleType.generateObject(new GeneratedProperties(Collections.emptyList()), 5L);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.valueOf(0.5));
	}

	@Test
    @DisplayName("generateObject should return 0 dot 05 when min 0 and max 10 and precision 2 for index 5")
	void generateObject_should_return_0_dot_05_when_min_0_and_max_10_and_precision_2_for_index_5() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0d), BigDecimal.valueOf(10d));
		int precision = 2;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
        GeneratedObject result = doubleType.generateObject(new GeneratedProperties(Collections.emptyList()), 5L);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.valueOf(0.05));
	}

	@Test
    @DisplayName("generateObject should throw exception when index is out of range")
	void generateObject_should_throw_exception_when_index_is_out_of_range() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0), BigDecimal.valueOf(1));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN

		// THEN
        assertThatThrownBy(() -> doubleType.generateObject(new GeneratedProperties(Collections.emptyList()), 100L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 0")
	void getMaxRange_should_return_0() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
		int precision = 0;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 1")
	void getMaxRange_should_return_1() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0d), BigDecimal.valueOf(1d));
		int precision = 0;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
    @DisplayName("getMaxRange should return 1000000")
	void getMaxRange_should_return_1000000() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(10), BigDecimal.valueOf(20d));
		int precision = 5;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1000000);
	}

	@Test
    @DisplayName("getMaxRange should return 5000")
	void getMaxRange_should_return_5000() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(12), BigDecimal.valueOf(17d));
		int precision = 3;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN
		Long result = doubleType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(5000L);
	}

	@Test
    @DisplayName("generateObject should throw exception when negative index is passed")
	void generateObject_should_throw_exception_when_negative_index_is_passed() {

		// GIVEN
        Range<BigDecimal> range = Range.withMinAndMax(BigDecimal.valueOf(0d), BigDecimal.valueOf(10d));
		int precision = 1;
		NumberRangeType doubleType = new NumberRangeType(range, precision);

		// WHEN

		// THEN
        assertThatThrownBy(() -> doubleType.generateObject(new GeneratedProperties(Collections.emptyList()), -1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}
}
