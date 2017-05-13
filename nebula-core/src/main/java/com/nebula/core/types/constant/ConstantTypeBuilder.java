package com.nebula.core.types.constant;

import java.math.BigDecimal;
import java.util.Date;

import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ConstantTypeBuilder implements TypeBuilder {

	private Object value;

	public ConstantTypeBuilder(String value) {
		this.value = value;
	}

	public ConstantTypeBuilder(BigDecimal value) {
		this.value = value;
	}

	public ConstantTypeBuilder(Date value) {
		this.value = value;
	}

	public ConstantTypeBuilder(Boolean value) {
		this.value = value;
	}

	@Override
	public Type build() {
		return new ConstantType(value);
	}

}
