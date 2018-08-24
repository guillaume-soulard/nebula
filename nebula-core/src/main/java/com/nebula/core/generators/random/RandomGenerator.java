package com.nebula.core.generators.random;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

import java.util.List;

class RandomGenerator implements Generator {

	private GenerationContext context;

	RandomGenerator() {

	}

    public GeneratedObject generate(List<GeneratedProperty> generatedProperties, Type type) {
		throwExceptionIfGenerationContextIsNull();
		type.init(context);
        return type.generateObject(generatedProperties, context.getNebulaRandom().nextIndex(type));
	}

	public void init(GenerationContext context) {
		this.context = context;
		throwExceptionIfGenerationContextIsNull();
	}

	private void throwExceptionIfGenerationContextIsNull() {
		if (context == null || context.getNebulaRandom() == null) {
			throw new NebulaException("generationContext is null");
		}
	}
}
