package com.nebula.core.types.date;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.date.strategy.*;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.nebula.core.types.date.DateTimeAdderConsumer.isInstanceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeRangeTypeTest {

	@Test
	public void generateObject_should_return_a_non_null_object() {

		// GIVEN
		DateTime dateTime = new DateTime(2017, 1, 1, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(dateTime, dateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
		GeneratedObject result = dateType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_2017_1_1() {

		// GIVEN
		DateTime dateTime = new DateTime(2017, 1, 1, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(dateTime, dateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
		GeneratedObject generatedObject = dateType.generateObject(0l);

		// THEN
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", dateTime);
	}

	@Test
	public void generateObject_should_return_2017_1_2() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeRangeType dateType = new DateTimeRangeType(range, DateTimeTypeIntervals.DAY);

		// WHEN
		GeneratedObject generatedObject = dateType.generateObject(1l);

		// THEN
		DateTime expectedDateTime = new DateTime(2017, 1, 2, 0, 0);
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", expectedDateTime);
	}

	@Test
	public void newDateType_should_set_DateTypeInterval_to_DAY_when_null_is_passed() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeRangeType dateType = null;

		// WHEN
		dateType = new DateTimeRangeType(range, null);

		// THEN
		assertThat(dateType).hasFieldOrPropertyWithValue("interval", DateTimeTypeIntervals.DAY);
	}

	@Test
	public void generateObject_should_return_2017_1_1_10_hours_and_00_minutes_for_interval_hours() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.HOUR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		GeneratedObject generatedObject = dateType.generateObject(9l);

		// THEN
		DateTime expectedDateTime = new DateTime(2017, 1, 1, 9, 0);
		assertThat(generatedObject).hasFieldOrPropertyWithValue("object", expectedDateTime);
	}

	@Test
	public void newDateTypeType_should_set_all_DateTimeIntervals_in_map() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = null;

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
	public void generateObject_should_throw_exception_when_reequested_date_is_out_of_range() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		catchException(dateType).generateObject(1000l);

		// THEN
		assertThat((Exception) (Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		catchException(dateType).generateObject(-1l);

		// THEN
		assertThat((Exception) (Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1000() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 0, 1, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MILLISECOND;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1000l);
	}

	@Test
	public void getMaxRange_should_return_10() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 0, 10, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.SECOND;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(10l);
	}

	@Test
	public void getMaxRange_should_return_36() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 36, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MINUTE;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(36l);
	}

	@Test
	public void getMaxRange_should_return_24() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 2, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.HOUR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(24l);
	}

	@Test
	public void getMaxRange_should_return_31() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(30l);
	}

	@Test
	public void getMaxRange_should_return_2() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 3, 1, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MONTH;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(2l);
	}

	@Test
	public void getMaxRange_should_return_6() {

		// GIVEN
		DateTime startDateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.YEAR;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(6l);
	}

	@Test
	public void getMaxRange_should_return_2_for_interval_minutes_and_for_an_interval_of_2_minutes_and_30_seconds() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 1, 0, 2, 30, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.MINUTE;
		DateTimeRangeType dateType = new DateTimeRangeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(2l);
	}
}