package com.nebula.core.types.date.strategy;

import org.joda.time.DateTime;

public interface DateTimeStrategy {

	DateTime getByDateAndIndexAndInterval(DateTime initialDate, Long index);

	Long getMaxIndex(DateTime min, DateTime max);
}
