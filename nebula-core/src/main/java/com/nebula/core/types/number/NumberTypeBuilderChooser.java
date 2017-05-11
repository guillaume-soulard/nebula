package com.nebula.core.types.number;

import java.math.BigDecimal;

import com.nebula.core.types.TypeBuilderChooser;

public class NumberTypeBuilderChooser
		extends TypeBuilderChooser<BigDecimal, NumberRangeTypeBuilder, NumberAmongTypeBuilder> {

	public NumberTypeBuilderChooser() {
		super(new NumberRangeTypeBuilder(), new NumberAmongTypeBuilder());
	}
}
