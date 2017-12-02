package com.nebula.core.types.date;

import com.nebula.core.Model;
import com.nebula.core.types.Range;
import com.nebula.core.types.RangeTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.parser.DateParser;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import java.util.Date;

public class DateTimeRangeTypeBuilder extends RangeTypeBuilder<ReadableInstant> {

	public static final DateTime MIN_DEFAULT_DATE_TIME = new DateTime(0l);
	public static final DateTime MAX_DEFAULT_DATE_TIME = new DateTime(9999, 12, 31, 0, 0);
	public static final Date MIN_DEFAULT_DATE = MIN_DEFAULT_DATE_TIME.toDate();
	public static final Date MAX_DEFAULT_DATE = MAX_DEFAULT_DATE_TIME.toDate();

	private DateTimeTypeIntervals interval;
	private DateParser dateParser = new DateParser();

	@Override
	public Type buildImpl(Model model) {
		return new DateTimeRangeType(new Range<>(min, max), interval);
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
		return MIN_DEFAULT_DATE_TIME;
	}

	@Override
	protected ReadableInstant getDefaultMax() {
		return MAX_DEFAULT_DATE_TIME;
	}

	@Override
	protected ReadableInstant parseItem(Model model, String itemString) {
		return dateParser.parse(model, itemString);
	}
}
