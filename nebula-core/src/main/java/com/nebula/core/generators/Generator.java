package com.nebula.core.generators;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

public interface Generator {
	void init(GenerationContext context);

    GeneratedObject generate(GeneratedProperties generatedProperties, Type type);
}
