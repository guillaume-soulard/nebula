package com.nebula.core.types.number;

import java.math.BigDecimal;
import java.util.List;

import com.nebula.core.types.AbstractAmongType;

public class NumberAmongType extends AbstractAmongType<BigDecimal> {

	public NumberAmongType(List<BigDecimal> items) {
		super(items);
	}
}
