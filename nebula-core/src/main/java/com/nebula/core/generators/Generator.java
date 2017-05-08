package com.nebula.core.generators;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.Type;

public interface Generator {

	void init(NebulaRandom nebulaRandom);

	GeneratedObject generate(Type type);
}
