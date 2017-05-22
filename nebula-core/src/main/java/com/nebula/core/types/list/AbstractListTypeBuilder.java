package com.nebula.core.types.list;

import com.nebula.core.NebulaException;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.TypeBuilder;

public abstract class AbstractListTypeBuilder implements TypeBuilder {

	protected int minSize = 0;
	protected int maxSize = 10;
	protected GeneratorBuilder generator;

	protected AbstractListTypeBuilder(GeneratorBuilder generator) {
		this.generator = generator;
	}

	public AbstractListTypeBuilder withMinSize(int value) {
		if (value < 0) {
			throw new NebulaException("minSize is negative");
		}
		this.minSize = value;
		return this;
	}

	public AbstractListTypeBuilder withMaxSize(int value) {
		this.maxSize = value;
		return this;
	}

	protected void throwExceptionWhenMinMaxAreNotValid() {
		if (minSize > maxSize) {
			throw new NebulaException("maxSize must be greater than minSize");
		}
	}

	protected void throwExceptionWhenGeneratorIsNull() {
		if (generator == null) {
			throw new NebulaException("generator is null");
		}
	}
}
