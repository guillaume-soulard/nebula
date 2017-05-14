package com.nebula.core.generators.sequance;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class SequanceGeneratorBuilder implements GeneratorBuilder {

	private boolean allowCycle = false;

	@Override
	public Generator build() {
		return new SequanceGenerator(allowCycle);
	}

	public SequanceGeneratorBuilder cycle() {
		this.allowCycle = true;
		return this;
	}
}
