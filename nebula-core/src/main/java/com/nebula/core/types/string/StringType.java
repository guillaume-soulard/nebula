package com.nebula.core.types.string;

import com.mifmif.common.regex.Generex;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
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
	public void init(NebulaRandom nebulaRandom) throws NebulaException {
		generex = new Generex(pattern, nebulaRandom.getRandom());
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {
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
