package com.nebula.core.types.number;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class DoubleTypeBuilder implements TypeBuilder {

	private int precision;
	private Double min = Double.MIN_VALUE;
	private Double max = Double.MAX_VALUE;

	@Override
	public Type build() throws NebulaException {
		return new DoubleType(new Range<Double>(min, max), precision);
	}

	public DoubleTypeBuilder withPrecision(int value) {
		precision = value;
		return this;
	}

	public DoubleTypeBuilder withMin(double value) {
		min = value;
		return this;
	}

	public DoubleTypeBuilder withMax(double value) {
		max = value;
		return this;
	}

}
