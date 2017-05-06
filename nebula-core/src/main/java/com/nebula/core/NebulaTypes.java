package com.nebula.core;

import com.nebula.core.types.date.DateTimeTypeBuilder;
import com.nebula.core.types.number.NumberTypeBuilder;

public final class NebulaTypes {

	public static NumberTypeBuilder number() {
		return new NumberTypeBuilder();
	}

	public static DateTimeTypeBuilder dateTime() {
		return new DateTimeTypeBuilder();
	}
}
