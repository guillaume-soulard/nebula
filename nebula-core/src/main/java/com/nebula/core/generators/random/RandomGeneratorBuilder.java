package com.nebula.core.generators.random;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class RandomGeneratorBuilder implements GeneratorBuilder {

	@Override
	public Generator build() {
		return new RandomGenerator();
	}

}
