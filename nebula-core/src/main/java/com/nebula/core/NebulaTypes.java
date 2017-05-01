package com.nebula.core;

import com.nebula.core.types.number.NumberTypeBuilder;

public final class NebulaTypes {

	public static NumberTypeBuilder number() throws NebulaException {

		return new NumberTypeBuilder();
	}
}
