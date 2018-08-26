package com.nebula.core.types.date;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.date.strategy.*;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.nebula.core.types.date.DateTimeAdderConsumer.isInstanceOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTimeRangeTypeTest {

	@Test
    @DisplayName("generateObject should return a non null object")
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		DateTime dateTime = new DateTime(2017, 1, 1, 0, 0);
		Range<ReadableInstant> range = new Range<>(dateTime, dateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
        GeneratedObject result = dateType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return 2017 1 1")
	void generateObject_should_return_2017_1_1() {

		// GIVEN
		DateTime dateTime = new DateTime(2017, 1, 1, 0, 0);
		Range<ReadableInstant> range = new Range<>(dateTime, dateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
        GeneratedObject generatedObject = dateType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", dateTime);
	}

	@Test
    @DisplayName("generateObject should return 2017 1 2")
	void generateObject_should_return_2017_1_2() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
        GeneratedObject generatedObject = dateType.generateObject(Collections.emptyList(), 1L);

		// THEN
		DateTime expectedDateTime = new DateTime(2017, 1, 2, 0, 0);
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", expectedDateTime);
	}

	@Test
    @DisplayName("newDateType should set DateTypeInterval to DAY when null is passed")
	void newDateType_should_set_DateTypeInterval_to_DAY_when_null_is_passed() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeRangeType dateType;

		// WHEN
		dateType = new DateTimeRangeType(range, null);

		// THEN
		assertThat(dateType).hasFieldOrPropertyWithValue("interval", DateTimeTypeIntervals.DAY);
	}

	@Test
    @DisplayName("generateObject should return 2017 1 1 10 hours and 00 minutes for interval hours")
	void generateObject_should_return_2017_1_1_10_hours_and_00_minutes_for_interval_hours() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.HOUR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
        GeneratedObject generatedObject = dateType.generateObject(Collections.emptyList(), 9L);

		// THEN
		DateTime expectedDateTime = new DateTime(2017, 1, 1, 9, 0);
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", expectedDateTime);
	}

	@Test
    @DisplayName("newDateTypeType should set all DateTimeIntervals in map")
	void newDateTypeType_should_set_all_DateTimeIntervals_in_map() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType;

		// WHEN
		dateType = new DateTimeRangeType(range, interval);

		// THEN
		assertThat(dateType.getDateTimeAdders())
				.containsOnlyKeys(DateTimeTypeIntervals.DAY, DateTimeTypeIntervals.HOUR,
						DateTimeTypeIntervals.MILLISECOND, DateTimeTypeIntervals.MINUTE, DateTimeTypeIntervals.MONTH,
						DateTimeTypeIntervals.SECOND, DateTimeTypeIntervals.YEAR)
				.hasEntrySatisfying(DateTimeTypeIntervals.DAY, isInstanceOf(DateTimeDayStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.HOUR, isInstanceOf(DateTimeHourStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.MILLISECOND, isInstanceOf(DateTimeMillisecondStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.MINUTE, isInstanceOf(DateTimeMinuteStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.MONTH, isInstanceOf(DateTimeMonthStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.SECOND, isInstanceOf(DateTimeSecondStrategy.class))
				.hasEntrySatisfying(DateTimeTypeIntervals.YEAR, isInstanceOf(DateTimeYearStrategy.class));
	}

	@Test
    @DisplayName("generateObject should throw exception when requested date is out of range")
	void generateObject_should_throw_exception_when_requested_date_is_out_of_range() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN

		// THEN
        assertThatThrownBy(() -> dateType.generateObject(Collections.emptyList(), 1000L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("generateObject should throw exception when index is negative")
	void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN

		// THEN
        assertThatThrownBy(() -> dateType.generateObject(Collections.emptyList(), -1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 1000")
	void getMaxRange_should_return_1000() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 0, 1, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MILLISECOND;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1000L);
	}

	@Test
    @DisplayName("getMaxRange should return 10")
	void getMaxRange_should_return_10() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 0, 10, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.SECOND;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(10L);
	}

	@Test
    @DisplayName("getMaxRange should return 36")
	void getMaxRange_should_return_36() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 36, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MINUTE;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(36L);
	}

	@Test
    @DisplayName("getMaxRange should return 24")
	void getMaxRange_should_return_24() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 2, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.HOUR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(24L);
	}

	@Test
    @DisplayName("getMaxRange should return 31")
	void getMaxRange_should_return_31() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(30L);
	}

	@Test
    @DisplayName("getMaxRange should return 2")
	void getMaxRange_should_return_2() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 3, 1, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MONTH;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(2L);
	}

	@Test
    @DisplayName("getMaxRange should return 6")
	void getMaxRange_should_return_6() {

		// GIVEN
		DateTime startDateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.YEAR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(6L);
	}

	@Test
    @DisplayName("getMaxRange should return 2 for interval minutes and for an interval of 2 minutes and 30 seconds")
	void getMaxRange_should_return_2_for_interval_minutes_and_for_an_interval_of_2_minutes_and_30_seconds() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 2, 30, 0);
		Range<ReadableInstant> range = new Range<>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MINUTE;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(2L);
	}
}