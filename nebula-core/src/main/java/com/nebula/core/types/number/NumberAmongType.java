package com.nebula.core.types.number;

import com.nebula.core.types.AbstractAmongType;

import java.math.BigDecimal;
import java.util.List;

class NumberAmongType extends AbstractAmongType<BigDecimal> {
	NumberAmongType(List<BigDecimal> items) {
		super(items);
	}
}
