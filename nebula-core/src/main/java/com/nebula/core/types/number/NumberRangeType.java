package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;

public class NumberRangeType implements Type {

	private Range<BigDecimal> range;
	private int precision;
	private BigDecimal increment;

	public NumberRangeType(Range<BigDecimal> range, int precision) {
		this.range = range;
		this.precision = precision;
		increment = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision));
	}

	public GeneratedObject generateObject(Long objectIndex) {
		BigDecimal result = range.getMin().add(new BigDecimal(objectIndex).multiply(increment)).setScale(precision);

		if (objectIndex < 0d || result.compareTo(range.getMax()) > 0) {
			throw new NebulaException("requested object is out of range");
		}
		return new GeneratedObject(result);
	}

	public Long getMinRange() {
		return range.getMin().longValue();
	}

	public Long getMaxRange() {
		return range.getMax().longValue();
	}

	@Override
	public void init(NebulaRandom nebulaRandom) {

	}
}
