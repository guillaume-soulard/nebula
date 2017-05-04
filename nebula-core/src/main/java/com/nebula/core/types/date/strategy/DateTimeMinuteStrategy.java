package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class DateTimeMinuteStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusMinutes(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Minutes.minutesBetween(min, max).getMinutes();
	}
}
