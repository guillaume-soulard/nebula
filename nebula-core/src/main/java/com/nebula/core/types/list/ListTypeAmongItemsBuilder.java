package com.nebula.core.types.list;

import com.nebula.core.Model;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.constant.ConstantTypeBuilder;

public class ListTypeAmongItemsBuilder extends AbstractListTypeBuilder {

	private ConstantTypeBuilder[] items = new ConstantTypeBuilder[] {};

	public ListTypeAmongItemsBuilder(GeneratorBuilder generator) {
		super(generator);
		throwExceptionWhenGeneratorIsNull();
	}

	@Override
	public Type build(Model model) {
		throwExceptionWhenMinMaxAreNotValid();
		return new ListTypeAmongItems(model, minSize, maxSize, generator.build(), items);
	}

	public ListTypeAmongItemsBuilder amongItems(ConstantTypeBuilder... items) {
		if (items == null) {
			this.items = new ConstantTypeBuilder[] { null };
		} else {
			this.items = items;
		}
		return this;
	}
}
