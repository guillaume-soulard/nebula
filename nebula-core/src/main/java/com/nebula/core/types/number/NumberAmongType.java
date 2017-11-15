package com.nebula.core.types.number;

import java.math.BigDecimal;
import java.util.List;

import com.nebula.core.types.AbstractAmongType;

class NumberAmongType extends AbstractAmongType<BigDecimal> {
	NumberAmongType(List<BigDecimal> items) {
		super(items);
	}
}
