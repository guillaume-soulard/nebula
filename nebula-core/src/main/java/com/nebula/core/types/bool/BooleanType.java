package com.nebula.core.types.bool;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Type;

import java.util.Collections;
import java.util.List;

class BooleanType implements Type {

	BooleanType() {

	}

	@Override
	public void init(GenerationContext context) {

	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		if (getMinRange().equals(objectIndex)) {
			return new GeneratedObject(Boolean.FALSE);
		} else if (getMaxRange().equals(objectIndex)) {
			return new GeneratedObject(Boolean.TRUE);
		} else {
			throw new NebulaException("requested object is out of range");
		}
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return 1l;
	}

	@Override
	public JavaType getJavaType() {
		return JavaType.BOOLEAN;
	}
}
