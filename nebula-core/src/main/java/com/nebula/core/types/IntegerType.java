package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;

public class IntegerType implements Type {

	private Range<Integer> range;

	public IntegerType(Range<Integer> range) {
		this.range = range;
	}

	public Range<Integer> getRange() {
		return range;
	}

	public GeneratedObject generateObject(int objectIndex) {
		return new GeneratedObject(range.getMin() + objectIndex);
	}

	public Integer getMinRange() {
		return range.getMin();
	}

	public Integer getMaxRange() {
		return range.getMax();
	}

	public void init(NebulaRandom nebulaRandom) {
		// TODO Auto-generated method stub

	}
}
