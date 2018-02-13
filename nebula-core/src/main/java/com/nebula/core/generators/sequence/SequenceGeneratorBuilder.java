package com.nebula.core.generators.sequence;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class SequenceGeneratorBuilder implements GeneratorBuilder {

	private boolean allowCycle = true;

	@Override
	public Generator build() {
		return new SequenceGenerator(allowCycle);
	}

	public SequenceGeneratorBuilder noCycle() {
		this.allowCycle = false;
		return this;
	}
}
