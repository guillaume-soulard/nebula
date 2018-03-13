package com.nebula.core.types.list;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ListTypeOfTypeBuilder extends AbstractListTypeBuilder {

	private final TypeBuilder type;

	public ListTypeOfTypeBuilder(GeneratorBuilder generator, TypeBuilder type) {
		super(generator);
		this.type = type;
		throwExceptionWhenGeneratorOrTypeAreNull();
	}

	@Override
	public Type build(Model model) {
		throwExceptionWhenMinMaxAreNotValid();
		return new ListTypeOfType(minSize, maxSize, generator.build(), type.build(model));
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
