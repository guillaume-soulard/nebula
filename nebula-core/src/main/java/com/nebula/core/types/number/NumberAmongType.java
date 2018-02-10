package com.nebula.core.types.number;

import com.nebula.core.types.AbstractAmongType;
import com.nebula.core.types.JavaType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class NumberAmongType extends AbstractAmongType<BigDecimal> {
	NumberAmongType(List<BigDecimal> items) {
		super(items);
	}

	@Override
	public JavaType getJavaType() {
		return JavaType.NUMBER;
	}
}
