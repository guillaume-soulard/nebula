package com.nebula.core.generators.sequence;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

import java.util.List;

class SequenceGenerator implements Generator {

	private final boolean allowCycle;
	private GenerationContext context;

	SequenceGenerator(boolean allowCycle) {
		this.allowCycle = allowCycle;
	}

	@Override
	public void init(GenerationContext context) {
		this.context = context;
	}

	@Override
    public GeneratedObject generate(List<GeneratedProperty> generatedProperties, Type type) {
		type.init(context);
		throwExceptionWhenIndexReachMaxIndexIfNeeded(type);
        return type.generateObject(generatedProperties, getEntityIndex(type));
	}

	private long getEntityIndex(Type type) {
		return context.getEntityIndex() % getMaxIndex(type);
	}

	private long getMaxIndex(Type type) {
		return type.getMaxRange() - type.getMinRange() + 1;
	}

	private void throwExceptionWhenIndexReachMaxIndexIfNeeded(Type type) {
		if (!allowCycle && context.getEntityIndex() > getMaxIndex(type)) {
			throw new NebulaException("sequence reach the maximum index of type (" + getMaxIndex(type)
					+ "). Use cycle() to allow sequence to restart");
		}
	}
}
