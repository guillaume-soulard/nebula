package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.RangeTypeBuilder;
import com.nebula.core.types.Type;

public class NumberRangeTypeBuilder extends RangeTypeBuilder<BigDecimal> {

	private int precision = 0;

	@Override
	public Type build() {
		if (min.compareTo(max) > 0) {
			throw new NebulaException("max must be greater than min");
		}
		return new NumberRangeType(new Range<BigDecimal>(min, max), precision);
	}

	public NumberRangeTypeBuilder withPrecision(int value) {
		if (value < 0) {
			throw new NebulaException("precision is negative");
		}
		precision = value;
		return this;
	}

	public NumberRangeTypeBuilder withMin(BigDecimal value) {
		min = value;
		return this;
	}

	public NumberRangeTypeBuilder withMax(BigDecimal value) {
		max = value;
		return this;
	}

	@Override
	protected BigDecimal getDefaultMin() {
		return BigDecimal.valueOf(Long.MIN_VALUE);
	}

	@Override
	protected BigDecimal getDefaultMax() {
		return BigDecimal.valueOf(Long.MAX_VALUE);
	}
}
