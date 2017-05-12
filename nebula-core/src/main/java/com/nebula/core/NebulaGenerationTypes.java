package com.nebula.core;

import com.nebula.core.types.bool.BooleanTypeBuilder;
import com.nebula.core.types.date.DateTimeTypeBuilderChooser;
import com.nebula.core.types.list.ListTypeBuilder;
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

	public static BooleanTypeBuilder bool() {
		return new BooleanTypeBuilder();
	}

	public static ListTypeBuilder list() {
		return new ListTypeBuilder();
	}
}
