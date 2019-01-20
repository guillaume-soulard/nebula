package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;

public interface Type {

	void init(GenerationContext context);

	GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex);

	Long getMinRange();

	Long getMaxRange();

	JavaType getJavaType();
}
