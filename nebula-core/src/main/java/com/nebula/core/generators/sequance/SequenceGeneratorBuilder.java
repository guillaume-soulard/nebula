package com.nebula.core.generators.sequance;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class SequenceGeneratorBuilder implements GeneratorBuilder {

	private boolean allowCycle = false;

	@Override
	public Generator build() {
		return new SequenceGenerator(allowCycle);
	}

	public SequenceGeneratorBuilder cycle() {
		this.allowCycle = true;
		return this;
	}
}
