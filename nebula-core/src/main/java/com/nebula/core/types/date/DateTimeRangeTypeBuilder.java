package com.nebula.core.types.date;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.nebula.core.types.Range;
import com.nebula.core.types.RangeTypeBuilder;
import com.nebula.core.types.Type;

public class DateTimeRangeTypeBuilder extends RangeTypeBuilder<ReadableInstant> {

	private DateTimeTypeIntervals interval;

	@Override
	public Type build() {
		return new DateTimeRangeType(new Range<ReadableInstant>(min, max), interval);
	}

	public DateTimeRangeTypeBuilder withMin(DateTime value) {
		this.min = value;
		return this;
	}

	public DateTimeRangeTypeBuilder withMax(DateTime value) {
		this.max = value;
		return this;
	}

	public DateTimeRangeTypeBuilder withInterval(DateTimeTypeIntervals value) {
		this.interval = value;
		return this;
	}

	@Override
	protected ReadableInstant getDefaultMin() {
		return new DateTime(0l);
	}

	@Override
	protected ReadableInstant getDefaultMax() {
		return new DateTime(9999, 12, 31, 0, 0);
	}
}
