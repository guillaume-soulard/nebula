package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

public class DateTimeSecondStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusSeconds(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return (long) Seconds.secondsBetween(min, max).getSeconds();
	}
}
