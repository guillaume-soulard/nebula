package com.nebula.core.types.number;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class LongTypeBuilder implements TypeBuilder {

	private Long min = Long.MIN_VALUE;
	private Long max = Long.MAX_VALUE;

	@Override
	public Type build() throws NebulaException {
		return new LongType(new Range<Long>(min, max));
	}

	public LongTypeBuilder withMin(long value) throws NebulaException {
		if (value > max) {
			throw new NebulaException("min should be less than max");
		}
		min = value;
		return this;
	}

	public LongTypeBuilder withMax(long value) throws NebulaException {
		if (value < min) {
			throw new NebulaException("max should be greater than min");
		}
		max = value;
		return this;
	}
}
