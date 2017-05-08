package com.nebula.core;

import com.nebula.core.types.date.DateTimeTypeBuilder;
import com.nebula.core.types.number.NumberTypeBuilder;
import com.nebula.core.types.string.StringTypeBuilder;

public final class NebulaTypes {

	public static NumberTypeBuilder number() {
		return new NumberTypeBuilder();
	}

	public static DateTimeTypeBuilder dateTime() {
		return new DateTimeTypeBuilder();
	}

	public static StringTypeBuilder string() {
		return new StringTypeBuilder();
	}
}
