package com.nebula.core.types.date;

import com.nebula.core.types.TypeBuilderChooser;
import org.joda.time.ReadableInstant;

public class DateTimeTypeBuilderChooser
		extends TypeBuilderChooser<DateTimeRangeTypeBuilder, DateTimeAmongTypeBuilder, ReadableInstant, DateTimeRangeTypeBuilder, DateTimeAmongTypeBuilder> {

	public DateTimeTypeBuilderChooser() {
		super(new DateTimeRangeTypeBuilder(), new DateTimeAmongTypeBuilder());
	}

}
