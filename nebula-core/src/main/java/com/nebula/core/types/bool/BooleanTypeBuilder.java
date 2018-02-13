package com.nebula.core.types.bool;

import com.nebula.core.Model;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class BooleanTypeBuilder implements RandomTypeBuilder {

	@Override
	public Type build(Model model) {
		return new BooleanType();
	}
}
