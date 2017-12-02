package com.nebula.core.types.number;

import com.nebula.core.types.TypeBuilderChooser;

import java.math.BigDecimal;

public class NumberTypeBuilderChooser
		extends TypeBuilderChooser<NumberRangeTypeBuilder, NumberAmongTypeBuilder, BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> {

	public NumberTypeBuilderChooser() {
		super(new NumberRangeTypeBuilder(), new NumberAmongTypeBuilder());
	}
}
