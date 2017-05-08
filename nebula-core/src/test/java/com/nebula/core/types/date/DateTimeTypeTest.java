package com.nebula.core.types.date;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.date.strategy.DateTimeDayStrategy;
import com.nebula.core.types.date.strategy.DateTimeHourStrategy;
import com.nebula.core.types.date.strategy.DateTimeMillisecondStrategy;
import com.nebula.core.types.date.strategy.DateTimeMinuteStrategy;
import com.nebula.core.types.date.strategy.DateTimeMonthStrategy;
import com.nebula.core.types.date.strategy.DateTimeSecondStrategy;
import com.nebula.core.types.date.strategy.DateTimeStrategy;
import com.nebula.core.types.date.strategy.DateTimeYearStrategy;

public class DateTimeTypeTest {

	@Test
	public void generateObject_should_return_a_non_null_object() {

		// GIVEN
		DateTime dateTime = new DateTime(2017, 1, 1, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(dateTime, dateTime);
		DateTimeType dateType = new DateTimeType(range, DateTimeTypeIntervals.DAY);

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
		DateTimeType dateType = new DateTimeType(range, DateTimeTypeIntervals.DAY);

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
		DateTimeType dateType = new DateTimeType(range, DateTimeTypeIntervals.DAY);

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
		DateTimeType dateType = null;

		// WHEN
		dateType = new DateTimeType(range, null);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = null;

		// WHEN
		dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

		// WHEN
		catchException(dateType).generateObject(1000l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void generateObject_should_throw_exception_when_index_is_negative() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeType dateType = new DateTimeType(range, interval);

		// WHEN
		catchException(dateType).generateObject(-1l);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		DateTime startDateTime = new DateTime(2017, 1, 1, 0, 0);
		DateTime endDateTime = new DateTime(2017, 1, 31, 0, 0);
		Range<ReadableInstant> range = new Range<ReadableInstant>(startDateTime, endDateTime);
		DateTimeTypeIntervals interval = DateTimeTypeIntervals.DAY;
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

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
		DateTimeType dateType = new DateTimeType(range, interval);

		// WHEN
		Long result = dateType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(2l);
	}

	private Consumer<DateTimeStrategy> isInstanceOf(Class<? extends DateTimeStrategy> expectedClass) {

		return new DateTimeAdderConsumer(expectedClass);
	}
}

class DateTimeAdderConsumer implements Consumer<DateTimeStrategy> {

	private Class<? extends DateTimeStrategy> clazz;

	public DateTimeAdderConsumer(Class<? extends DateTimeStrategy> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void accept(DateTimeStrategy t) {
		assertThat(t).isInstanceOf(clazz);
	}

}
