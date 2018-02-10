package com.nebula.core.types.date;

import com.nebula.core.types.AbstractAmongType;
import com.nebula.core.types.JavaType;
import org.joda.time.ReadableInstant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class DateTimeAmongType extends AbstractAmongType<ReadableInstant> {
	DateTimeAmongType(List<ReadableInstant> items) {
		super(items);
	}

	@Override
	public JavaType getJavaType() {
		return JavaType.DATE;
	}
}
