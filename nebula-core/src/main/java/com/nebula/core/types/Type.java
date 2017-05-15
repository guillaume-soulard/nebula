package com.nebula.core.types;

import com.nebula.core.GeneratedObject;

public interface Type {

	void init(GenerationContext context);

	GeneratedObject generateObject(Long objectIndex);

	Long getMinRange();

	Long getMaxRange();
}
