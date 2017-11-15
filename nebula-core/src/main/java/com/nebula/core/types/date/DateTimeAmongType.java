package com.nebula.core.types.date;

import com.nebula.core.types.AbstractAmongType;
import org.joda.time.ReadableInstant;

import java.util.List;

class DateTimeAmongType extends AbstractAmongType<ReadableInstant> {
	DateTimeAmongType(List<ReadableInstant> items) {
		super(items);
	}
}
