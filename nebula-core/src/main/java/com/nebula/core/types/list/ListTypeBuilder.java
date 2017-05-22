package com.nebula.core.types.list;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.TypeBuilder;

public class ListTypeBuilder {

	public ListTypeOfTypeBuilder of(GeneratorBuilder generator, TypeBuilder typeBuilder) {
		return new ListTypeOfTypeBuilder(generator, typeBuilder);
	}

	public ListTypeAmongItemsBuilder of(GeneratorBuilder generator) {
		return new ListTypeAmongItemsBuilder(generator);
	}
}
