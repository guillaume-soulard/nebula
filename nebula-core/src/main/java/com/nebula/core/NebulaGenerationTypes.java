package com.nebula.core;

import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.number.NumberTypeBuilderChooser;
import com.nebula.core.types.string.StringTypeBuilder;

public final class NebulaGenerationTypes {

	public static NumberTypeBuilderChooser number() {
		return new NumberTypeBuilderChooser();
	}

	public static DateTimeTypeBuilderChooser dateTime() {
		return new DateTimeTypeBuilderChooser();
	}

	public static StringTypeBuilder string() {
		return new StringTypeBuilder();
	}
}
