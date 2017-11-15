package com.nebula.core.types.date;

import java.util.List;

import org.joda.time.ReadableInstant;

import com.nebula.core.types.AbstractAmongType;

class DateTimeAmongType extends AbstractAmongType<ReadableInstant> {
	DateTimeAmongType(List<ReadableInstant> items) {
		super(items);
	}
}
