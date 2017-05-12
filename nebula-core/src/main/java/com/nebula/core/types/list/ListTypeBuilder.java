package com.nebula.core.types.list;

import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ListTypeBuilder implements TypeBuilder {

	private Generator generator;
	private TypeBuilder typeBuilder;
	private int minSize = 0;
	private int maxSize = 10;

	@Override
	public Type build() {
		if (minSize > maxSize) {
			throw new NebulaException("maxSize must be greater than minSize");
		}
		return new ListType(minSize, maxSize, generator, typeBuilder.build());
	}

	public ListTypeBuilder of(Generator generator, TypeBuilder typeBuilder) {
		if (generator == null) {
			throw new NebulaException("generator is null");
		}
		if (typeBuilder == null) {
			throw new NebulaException("typeBuilder is null");
		}
		this.generator = generator;
		this.typeBuilder = typeBuilder;
		return this;
	}

	public ListTypeBuilder withMinSize(int value) {
		if (value < 0) {
			throw new NebulaException("minSize is negative");
		}
		this.minSize = value;
		return this;
	}

	public ListTypeBuilder withMaxSize(int value) {
		this.maxSize = value;
		return this;
	}
}
