package com.nebula.core.types.date;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.AbstractTypeWithIndexCheck;
import com.nebula.core.types.Range;
import com.nebula.core.types.date.strategy.*;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import java.util.HashMap;
import java.util.Map;

class DateTimeRangeType extends AbstractTypeWithIndexCheck {

	private Range<ReadableInstant> range;
	private DateTimeTypeIntervals interval;
	private Map<DateTimeTypeIntervals, DateTimeStrategy> dateTimeStrategy = new HashMap<>();

	DateTimeRangeType(Range<ReadableInstant> range, DateTimeTypeIntervals interval) {
		this.range = range;
		if (interval == null) {
			this.interval = DateTimeTypeIntervals.DAY;
		} else {
			this.interval = interval;
		}

		dateTimeStrategy.put(DateTimeTypeIntervals.DAY, new DateTimeDayStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.HOUR, new DateTimeHourStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.MILLISECOND, new DateTimeMillisecondStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.MINUTE, new DateTimeMinuteStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.MONTH, new DateTimeMonthStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.SECOND, new DateTimeSecondStrategy());
		dateTimeStrategy.put(DateTimeTypeIntervals.YEAR, new DateTimeYearStrategy());
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		DateTime min = (DateTime) range.getMin();
		DateTime max = (DateTime) range.getMax();

		return dateTimeStrategy.get(interval).getMaxIndex(min, max);
	}

	public Map<DateTimeTypeIntervals, DateTimeStrategy> getDateTimeAdders() {
		return new HashMap<>(dateTimeStrategy);
	}

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
		return new GeneratedObject(calculateRequestedDate(index));
	}

	public DateTime calculateRequestedDate(Long index) {
		return dateTimeStrategy.get(interval).getByDateAndIndexAndInterval((DateTime) range.getMin(), index);
	}

}
