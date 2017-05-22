package com.nebula.core.types.list;

import com.nebula.core.NebulaException;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ListTypeOfTypeBuilder extends AbstractListTypeBuilder {

	private TypeBuilder type;

	public ListTypeOfTypeBuilder(GeneratorBuilder generator, TypeBuilder type) {
		super(generator);
		this.type = type;
		throwExceptionWhenGeneratorOrTypeAreNull();
	}

	@Override
	public Type build() {
		throwExceptionWhenMinMaxAreNotValid();
		return new ListTypeOfType(minSize, maxSize, generator.build(), type.build());
	}

	private void throwExceptionWhenGeneratorOrTypeAreNull() {
		throwExceptionWhenGeneratorIsNull();
		throwExceptionWhenBuilderIsNull();
	}

	private void throwExceptionWhenBuilderIsNull() {
		if (type == null) {
			throw new NebulaException("type is null");
		}
	}
}
