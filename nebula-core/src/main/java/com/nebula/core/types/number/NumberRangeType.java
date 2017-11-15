package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.AbstractTypeWithIndexCheck;
import com.nebula.core.types.Range;

class NumberRangeType extends AbstractTypeWithIndexCheck {

	private Range<BigDecimal> range;
	private int precision;
	private BigDecimal increment;

	NumberRangeType(Range<BigDecimal> range, int precision) {
		this.range = range;
		this.precision = precision;
		increment = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision));
	}

	public Long getMinRange() {
		return 0l;
	}

	public Long getMaxRange() {
		return BigDecimal.TEN.pow(precision).multiply(range.getMax().subtract(range.getMin())).longValue();
	}

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
		return new GeneratedObject(calculateRequestedNumber(index));
	}

	private BigDecimal calculateRequestedNumber(Long index) {
		return range.getMin().add(new BigDecimal(index).multiply(increment)).setScale(precision);
	}
}
