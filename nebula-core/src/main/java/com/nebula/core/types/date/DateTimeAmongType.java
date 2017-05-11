package com.nebula.core.types.date;

import java.util.List;

import org.joda.time.ReadableInstant;

import com.nebula.core.types.AbstractAmongType;

public class DateTimeAmongType extends AbstractAmongType<ReadableInstant> {

	public DateTimeAmongType(List<ReadableInstant> items) {
		super(items);
	}
}
