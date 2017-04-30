package com.nebula.core;

import com.nebula.core.types.IntegerType;
import com.nebula.core.types.Range;

public final class NebulaTypes {

	public static IntegerType integer() throws NebulaException {

		return integerRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static IntegerType integerRange(Integer min, Integer max) throws NebulaException {

		return new IntegerType(new Range<Integer>(min, max));
	}

	public static IntegerType unsignedInteger() throws NebulaException {

		return integerRange(0, Integer.MAX_VALUE);
	}
}
