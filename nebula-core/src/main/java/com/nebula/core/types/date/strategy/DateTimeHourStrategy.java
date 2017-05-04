package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public class DateTimeHourStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusHours(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Hours.hoursBetween(min, max).getHours();
	}
}
