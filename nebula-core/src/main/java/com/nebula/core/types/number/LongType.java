package com.nebula.core.types.number;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Range;
import com.nebula.core.types.Type;

public class LongType implements Type {

	private Range<Long> range;

	public LongType(Range<Long> range) {
		this.range = range;
	}

	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {
		if (objectIndex < 0l || range.getMin() + objectIndex > range.getMax()) {
			throw new NebulaException("requested object is out of range");
		}
		return new GeneratedObject(range.getMin() + objectIndex);
	}

	public Long getMinRange() {
		return range.getMin();
	}

	public Long getMaxRange() {
		return range.getMax();
	}

	@Override
	public void init(NebulaRandom nebulaRandom) throws NebulaException {

	}
}
