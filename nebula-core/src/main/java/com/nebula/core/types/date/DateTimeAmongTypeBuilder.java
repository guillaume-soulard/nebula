package com.nebula.core.types.date;

import org.joda.time.ReadableInstant;

import com.nebula.core.types.AmongTypeBuilder;
import com.nebula.core.types.Type;

public class DateTimeAmongTypeBuilder extends AmongTypeBuilder<ReadableInstant> {

	@Override
	public Type build() {
		return new DateTimeAmongType(items);
	}
}
