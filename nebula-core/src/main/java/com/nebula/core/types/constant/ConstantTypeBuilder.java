package com.nebula.core.types.constant;

import com.nebula.core.Model;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class ConstantTypeBuilder implements TypeBuilder {

	private Object value;

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
