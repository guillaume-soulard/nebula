package com.nebula.core.types.number;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.AbstractTypeWithIndexCheck;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;

class NumberRangeType extends AbstractTypeWithIndexCheck {

	private final Range<BigDecimal> range;
	private final int precision;
	private final BigDecimal increment;

	NumberRangeType(Range<BigDecimal> range, int precision) {
		this.range = range;
		this.precision = precision;
		increment = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision), precision);
	}

	public Long getMinRange() {
		return 0L;
	}

	public Long getMaxRange() {
		return BigDecimal.TEN.pow(precision).multiply(range.getMax().subtract(range.getMin())).longValue();
	}

	@Override
	public JavaType getJavaType() {
		return JavaType.NUMBER;
	}

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
		return new GeneratedObject(calculateRequestedNumber(index));
	}

	private BigDecimal calculateRequestedNumber(Long index) {
		return range.getMin().add(new BigDecimal(index).multiply(increment)).setScale(precision, RoundingMode.HALF_EVEN);
	}
}
