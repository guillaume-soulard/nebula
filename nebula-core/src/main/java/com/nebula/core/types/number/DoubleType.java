package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;

public class DoubleType implements Type {

	private Range<Double> range;
	private int precision;

	public DoubleType(Range<Double> range, int precision) {
		this.range = range;
		this.precision = precision;
	}

	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {
		double increment = 1 / (Math.pow(10, precision));
		if (objectIndex < 0d || range.getMin() + (objectIndex * increment) > range.getMax()) {
			throw new NebulaException("requested object is out of range");
		}
		return new GeneratedObject(range.getMin() + (objectIndex * increment));
	}

	public Long getMinRange() {
		return new BigDecimal(range.getMin()).longValue();
	}

	public Long getMaxRange() {
		return new BigDecimal(range.getMax()).longValue();
	}

	@Override
	public void init(NebulaRandom nebulaRandom) throws NebulaException {

	}
}
