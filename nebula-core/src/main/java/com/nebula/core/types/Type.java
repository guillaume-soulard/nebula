package com.nebula.core.types;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;

public interface Type {

	void init(NebulaRandom nebulaRandom) throws NebulaException;

	GeneratedObject generateObject(Long objectIndex) throws NebulaException;

	Long getMinRange();

	Long getMaxRange();
}
