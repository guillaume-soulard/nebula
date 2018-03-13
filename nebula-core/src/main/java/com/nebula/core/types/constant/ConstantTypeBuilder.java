package com.nebula.core.types.constant;

import com.nebula.core.Model;
import com.nebula.core.types.StaticTypeBuilder;
import com.nebula.core.types.Type;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class ConstantTypeBuilder implements StaticTypeBuilder {

	private final Object value;

	public ConstantTypeBuilder(String value) {
		this.value = value;
	}

	public ConstantTypeBuilder(BigDecimal value) {
		this.value = value;
	}

	public ConstantTypeBuilder(DateTime value) {
		this.value = value;
	}

	public ConstantTypeBuilder(Boolean value) {
		this.value = value;
	}

	@Override
	public Type build(Model model) {
		return new ConstantType(value);
	}

}
