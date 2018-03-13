package com.nebula.core.types.string;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;

import static com.nebula.core.types.string.StringGenerator.newStringGenerator;

public class StringTypeBuilder implements RandomTypeBuilder {

	private String pattern;

	@Override
	public Type build(Model model) {
		return new StringType(newStringGenerator(pattern));
	}

	public StringTypeBuilder withPattern(String value) {
		if (value == null) {
			throw new NebulaException("pattern is null");
		}
		this.pattern = value;
		return this;
	}
}
