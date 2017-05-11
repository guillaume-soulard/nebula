package com.nebula.core.types.date;

import org.joda.time.ReadableInstant;

import com.nebula.core.types.TypeBuilderChooser;

public class DateTimeTypeBuilderChooser
		extends TypeBuilderChooser<ReadableInstant, DateTimeRangeTypeBuilder, DateTimeAmongTypeBuilder> {

	public DateTimeTypeBuilderChooser() {
		super(new DateTimeRangeTypeBuilder(), new DateTimeAmongTypeBuilder());
	}

}
