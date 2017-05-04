package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;

public interface DateTimeStrategy {

	public DateTime getByDateAndIndexAndInterval(DateTime initialDate, Long index);

	public Long getMaxIndex(DateTime min, DateTime max);
}
