package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;

public class DateTimeMillisecondStrategy extends AbstractDateTimeAdder {

	@Override
	protected DateTime addToDate(DateTime initialDate, int amount) {
		return initialDate.plusMillis(amount);
	}

	@Override
	public Long getMaxIndex(DateTime min, DateTime max) {
		return max.getMillis() - min.getMillis();
	}
}
