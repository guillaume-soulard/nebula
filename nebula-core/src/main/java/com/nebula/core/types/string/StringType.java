package com.nebula.core.types.string;

import com.mifmif.common.regex.Generex;
import com.nebula.core.GeneratedObject;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

public class StringType implements Type {

	private Generex generex;
	private String pattern;

	public StringType(String pattern) {
		if (pattern == null) {
			this.pattern = "[a-zA-Z_0-9]{10}";
		} else {
			this.pattern = pattern;
		}
	}

	@Override
	public void init(GenerationContext context) {
		generex = new Generex(pattern, context.getNebulaRandom().getRandom());
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		return new GeneratedObject(generex.random());
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return 0l;
	}

}
