package com.nebula.core.types.constant;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import com.nebula.core.types.Type;
import org.joda.time.DateTime;

import java.math.BigDecimal;

class ConstantType implements Type {

	private final Object value;

	ConstantType(Object value) {
		this.value = value;
	}

	@Override
	public void init(GenerationContext context) {

	}

	@Override
    public GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex) {
        return GeneratedObject.of(value);
	}

	@Override
	public Long getMinRange() {
		return 0L;
	}

	@Override
	public Long getMaxRange() {
		return 0L;
	}

	@Override
	public JavaType getJavaType() {
		if (value instanceof Boolean) {
			return JavaType.BOOLEAN;
		}

		if (value instanceof String) {
			return JavaType.STRING;
		}

		if (value instanceof BigDecimal) {
			return JavaType.NUMBER;
		}

		if (value instanceof DateTime) {
			return JavaType.DATE;
		}

		return new JavaType(JavaTypeName.OBJECT);
	}

}
