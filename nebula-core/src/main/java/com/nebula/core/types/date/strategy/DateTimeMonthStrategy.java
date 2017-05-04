package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Months;

public class DateTimeMonthStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusMonths(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Months.monthsBetween(min, max).getMonths();
	}
}
