package com.nebula.core.types.list;

import com.nebula.core.NebulaException;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ListTypeBuilder implements TypeBuilder {

	private GeneratorBuilder generator;
	private TypeBuilder typeBuilder;
	private int minSize = 0;
	private int maxSize = 10;

	@Override
	public Type build() {
		checkGeneratorAndType();
		if (minSize > maxSize) {
			throw new NebulaException("maxSize must be greater than minSize");
		}
		return new ListType(minSize, maxSize, generator.build(), typeBuilder.build());
	}

	private void checkGeneratorAndType() {
		if (generator == null) {
			throw new NebulaException("generator is null");
		}
		if (typeBuilder == null) {
			throw new NebulaException("typeBuilder is null");
		}
	}

	public ListTypeBuilder of(GeneratorBuilder generator, TypeBuilder typeBuilder) {
		this.generator = generator;
		this.typeBuilder = typeBuilder;
		checkGeneratorAndType();
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
