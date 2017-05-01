package com.nebula.core;

import com.nebula.core.types.integer.IntegerTypeBuilder;

public final class NebulaTypes {

	public static IntegerTypeBuilder integer() throws NebulaException {

		return new IntegerTypeBuilder();
	}
}
