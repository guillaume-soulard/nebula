package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;

import java.util.List;

public interface Type {

	void init(GenerationContext context);

    GeneratedObject generateObject(List<GeneratedProperty> generatedProperties, Long objectIndex);

	Long getMinRange();

	Long getMaxRange();

	JavaType getJavaType();
}
