package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.types.AmongTypeBuilder;
import com.nebula.core.types.Type;

public class NumberAmongTypeBuilder extends AmongTypeBuilder<BigDecimal> {

	@Override
	public Type build() {
		return new NumberAmongType(items);
	}
}
