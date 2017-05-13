package com.nebula.core.types.constant;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class ConstantType implements Type {

	private Object value;

	public ConstantType(Object value) {
		this.value = value;
	}

	@Override
	public void init(NebulaRandom nebulaRandom) {

	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		return new GeneratedObject(value);
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return 0l;
	}

}
