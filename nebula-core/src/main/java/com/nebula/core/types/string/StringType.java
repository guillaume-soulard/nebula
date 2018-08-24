package com.nebula.core.types.string;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Type;

import java.util.List;

class StringType implements Type {

	private final StringGenerator stringGenerator;

	StringType(StringGenerator stringGenerator) {
		this.stringGenerator = stringGenerator;
	}

	@Override
	public void init(GenerationContext context) {
		stringGenerator.setSeed(context.getNebulaRandom().getSeed());
	}

	@Override
    public GeneratedObject generateObject(List<GeneratedProperty> generatedProperties, Long objectIndex) {
		return new GeneratedObject(stringGenerator.generateString());
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
		return JavaType.STRING;
	}
}
