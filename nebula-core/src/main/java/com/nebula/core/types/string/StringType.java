package com.nebula.core.types.string;

import com.nebula.core.GeneratedObject;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Type;

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
	public GeneratedObject generateObject(Long objectIndex) {
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
