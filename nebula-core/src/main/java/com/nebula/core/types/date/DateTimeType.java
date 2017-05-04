package com.nebula.core.types.date;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;
import com.nebula.core.types.date.strategy.DateTimeDayStrategy;
import com.nebula.core.types.date.strategy.DateTimeHourStrategy;
import com.nebula.core.types.date.strategy.DateTimeMillisecondStrategy;
import com.nebula.core.types.date.strategy.DateTimeMinuteStrategy;
import com.nebula.core.types.date.strategy.DateTimeMonthStrategy;
import com.nebula.core.types.date.strategy.DateTimeSecondStrategy;
import com.nebula.core.types.date.strategy.DateTimeStrategy;
import com.nebula.core.types.date.strategy.DateTimeYearStrategy;

public class DateTimeType implements Type {

	private Range<ReadableInstant> range;
	private DateTimeTypeIntervals interval;
	private Map<DateTimeTypeIntervals, DateTimeStrategy> dateTimestrategy = new HashMap<DateTimeTypeIntervals, DateTimeStrategy>();

	public DateTimeType(Range<ReadableInstant> range, DateTimeTypeIntervals interval) {
		this.range = range;
		if (interval == null) {
			this.interval = DateTimeTypeIntervals.DAY;
		} else {
			this.interval = interval;
		}

		dateTimestrategy.put(DateTimeTypeIntervals.DAY, new DateTimeDayStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.HOUR, new DateTimeHourStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.MILLISECOND, new DateTimeMillisecondStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.MINUTE, new DateTimeMinuteStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.MONTH, new DateTimeMonthStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.SECOND, new DateTimeSecondStrategy());
		dateTimestrategy.put(DateTimeTypeIntervals.YEAR, new DateTimeYearStrategy());
	}

	@Override
	public void init(NebulaRandom nebulaRandom) throws NebulaException {

	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {

		DateTime requestedDate = dateTimestrategy.get(interval).getByDateAndIndexAndInterval((DateTime) range.getMin(),
				objectIndex);

		if (requestedDate.isAfter(range.getMax()) || objectIndex < 0) {
			throw new NebulaException("requested object is out of range");
		}

		return new GeneratedObject(requestedDate);
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		DateTime min = (DateTime) range.getMin();
		DateTime max = (DateTime) range.getMax();

		return dateTimestrategy.get(interval).getMaxIndex(min, max);
	}

	public Map<DateTimeTypeIntervals, DateTimeStrategy> getDateTimeAdders() {
		return new HashMap<DateTimeTypeIntervals, DateTimeStrategy>(dateTimestrategy);
	}

}
