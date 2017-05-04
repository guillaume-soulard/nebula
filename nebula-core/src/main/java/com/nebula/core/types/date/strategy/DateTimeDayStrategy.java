package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateTimeDayStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusDays(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Days.daysBetween(min, max).getDays();
	}
}
