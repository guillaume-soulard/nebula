package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;

public abstract class AbstractTypeWithIndexCheck implements Type {

	protected NebulaRandom nebulaRandom;

	@Override
	public void init(NebulaRandom nebulaRandom) {
		this.nebulaRandom = nebulaRandom;
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		if (objectIndex == null || objectIndex < getMinRange() || objectIndex > getMaxRange()) {
			throw new NebulaException("requested object is out of range");
		}
		return generatedObjectAtIndex(objectIndex);
	}

	protected abstract GeneratedObject generatedObjectAtIndex(Long index);
}
