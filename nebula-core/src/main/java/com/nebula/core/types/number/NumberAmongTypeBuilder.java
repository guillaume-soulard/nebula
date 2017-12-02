package com.nebula.core.types.number;

import com.nebula.core.Model;
import com.nebula.core.types.AmongTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.parser.NumberParser;

import java.math.BigDecimal;

public class NumberAmongTypeBuilder extends AmongTypeBuilder<BigDecimal> {

	private NumberParser numberParser = new NumberParser();

	@Override
	public Type buildImpl(Model model) {
		return new NumberAmongType(items);
	}

	@Override
	protected BigDecimal parseItem(Model model, String itemString) {
		return numberParser.parse(model, itemString);
	}
}
