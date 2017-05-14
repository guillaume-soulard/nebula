package com.nebula.core.generators.random;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class RandomGenerator implements Generator {

	private NebulaRandom nebulaRandom;

	public GeneratedObject generate(Type type) {
		throwExceptionIfNebulaRandomIsNull(nebulaRandom);
		return type.generateObject(nebulaRandom.nextIndex(type));
	}

	public void init(NebulaRandom nebulaRandom) {
		throwExceptionIfNebulaRandomIsNull(nebulaRandom);
		this.nebulaRandom = nebulaRandom;
	}

	public void throwExceptionIfNebulaRandomIsNull(NebulaRandom nebulaRandom) {
		if (nebulaRandom == null) {
			throw new NebulaException("nebulaRandom is null");
		}
	}
}
