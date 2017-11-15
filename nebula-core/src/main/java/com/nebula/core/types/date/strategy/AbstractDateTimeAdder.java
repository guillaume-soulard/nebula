package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public abstract class AbstractDateTimeAdder implements DateTimeStrategy {

	@Override
	public DateTime getByDateAndIndexAndInterval(DateTime initialDate, Long index) {
		return addToDate(initialDate, getAmountByIndex(index));
	}

	private int getAmountByIndex(Long index) {
		return new BigDecimal(index).intValue();
	}

	protected abstract DateTime addToDate(DateTime initialDate, int amount);
}
