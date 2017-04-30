package com.nebula.core.generators;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;

public class RandomGenerator implements Generator {

	private NebulaRandom nebulaRandom;

	public GeneratedObject generate(Type type) throws NebulaException {
		throwExceptionIfNebulaRandomIsNull(nebulaRandom);
		return type.generateObject(nebulaRandom.nextIndex(type));
	}

	public void init(NebulaRandom nebulaRandom) throws NebulaException {
		throwExceptionIfNebulaRandomIsNull(nebulaRandom);
		this.nebulaRandom = nebulaRandom;
	}

	public void throwExceptionIfNebulaRandomIsNull(NebulaRandom nebulaRandom) throws NebulaException {
		if (nebulaRandom == null) {
			throw new NebulaException("nebulaRandom is null");
		}
	}
}
