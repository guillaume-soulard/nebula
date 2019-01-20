package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.GeneratedProperties;

public abstract class AbstractTypeWithIndexCheck implements Type {

	protected GenerationContext context;

	@Override
	public void init(GenerationContext context) {
		this.context = context;
	}

	@Override
    public GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex) {
		if (objectIndex == null || objectIndex < getMinRange() || objectIndex > getMaxRange()) {
			throw new NebulaException("requested object is out of range");
		}
		return generatedObjectAtIndex(objectIndex);
	}

	protected abstract GeneratedObject generatedObjectAtIndex(Long index);
}
