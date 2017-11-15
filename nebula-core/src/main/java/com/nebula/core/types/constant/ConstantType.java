package com.nebula.core.types.constant;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

class ConstantType implements Type {

	private Object value;

	ConstantType(Object value) {
		this.value = value;
	}

	@Override
	public void init(GenerationContext context) {

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
