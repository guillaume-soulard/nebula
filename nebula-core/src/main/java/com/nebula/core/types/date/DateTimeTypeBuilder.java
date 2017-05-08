package com.nebula.core.types.date;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class DateTimeTypeBuilder implements TypeBuilder {

	private DateTime min = new DateTime(Long.MIN_VALUE);
	private DateTime max = new DateTime(Long.MAX_VALUE);
	private DateTimeTypeIntervals interval;

	@Override
	public Type build() {
		return new DateTimeType(new Range<ReadableInstant>(min, max), interval);
	}

	public DateTimeTypeBuilder withMin(DateTime value) {
		this.min = value;
		return this;
	}

	public DateTimeTypeBuilder withMax(DateTime value) {
		this.max = value;
		return this;
	}

	public DateTimeTypeBuilder withInterval(DateTimeTypeIntervals value) {
		this.interval = value;
		return this;
	}
}
