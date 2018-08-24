package com.nebula.core.generators;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

import java.util.List;

public interface Generator {
	void init(GenerationContext context);

    GeneratedObject generate(List<GeneratedProperty> generatedProperties, Type type);
}
