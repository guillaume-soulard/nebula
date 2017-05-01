package com.nebula.core.types.integer;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class IntegerTypeBuilder implements TypeBuilder {

	private Integer min = Integer.MIN_VALUE;
	private Integer max = Integer.MAX_VALUE;

	@Override
	public Type build() throws NebulaException {
		return new IntegerType(new Range<Integer>(min, max));
	}

	public IntegerTypeBuilder withMin(int value) throws NebulaException {
		if (value > max) {
			throw new NebulaException("min should be less than max");
		}
		min = value;
		return this;
	}

	public IntegerTypeBuilder withMax(int value) throws NebulaException {
		if (value < min) {
			throw new NebulaException("max should be greater than min");
		}
		max = value;
		return this;
	}
}
