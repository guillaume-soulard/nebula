package com.nebula.core.generators;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public interface Generator {

	void init(NebulaRandom nebulaRandom) throws NebulaException;

	GeneratedObject generate(Type type) throws NebulaException;
}
