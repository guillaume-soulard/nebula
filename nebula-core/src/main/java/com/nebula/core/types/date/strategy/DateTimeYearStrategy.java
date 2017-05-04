package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Years;

public class DateTimeYearStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusYears(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Years.yearsBetween(min, max).getYears();
	}
}
