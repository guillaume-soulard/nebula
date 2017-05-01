package com.nebula.core.types.number;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;

public class NumberType implements Type {

	private Range<Long> range;

	public NumberType(Range<Long> range) {
		this.range = range;
	}

	public GeneratedObject generateObject(Long objectIndex) {
		return new GeneratedObject(range.getMin() + objectIndex);
	}

	public Long getMinRange() {
		return range.getMin();
	}

	public Long getMaxRange() {
		return range.getMax();
	}

	public void init(NebulaRandom nebulaRandom) {

	}
}
