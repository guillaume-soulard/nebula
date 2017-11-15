package com.nebula.core.types.number;

import com.nebula.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Range;
import com.nebula.core.types.RangeTypeBuilder;
import com.nebula.core.types.Type;
import com.nebula.parser.NumberParser;

import java.math.BigDecimal;

public class NumberRangeTypeBuilder extends RangeTypeBuilder<BigDecimal> {

	private int precision = 0;
	private NumberParser numberParser = new NumberParser();

	@Override
	protected Type buildImpl(Model model) {
		if (min.compareTo(max) > 0) {
			throw new NebulaException("max must be greater than min");
		}
		return new NumberRangeType(new Range<>(min, max), precision);
	}

	public NumberRangeTypeBuilder withPrecision(int value) {
		if (value < 0) {
			throw new NebulaException("precision is negative");
		}
		precision = value;
		return this;
	}

	public NumberRangeTypeBuilder withMin(BigDecimal value) {
		min = value;
		return this;
	}

	public NumberRangeTypeBuilder withMax(BigDecimal value) {
		max = value;
		return this;
	}

	@Override
	protected BigDecimal getDefaultMin() {
		return BigDecimal.valueOf(Integer.MIN_VALUE);
	}

	@Override
	protected BigDecimal getDefaultMax() {
		return BigDecimal.valueOf(Integer.MAX_VALUE);
	}

	@Override
	protected BigDecimal parseItem(Model model, String itemString) {
		return numberParser.parse(model, itemString);
	}
}
