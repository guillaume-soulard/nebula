package com.nebula.core.types.bool;

import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class BooleanTypeBuilder implements TypeBuilder {

	@Override
	public Type build() {
		return new BooleanType();
	}
}
