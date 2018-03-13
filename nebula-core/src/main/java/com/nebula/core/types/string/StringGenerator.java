package com.nebula.core.types.string;

import com.mifmif.common.regex.Generex;

public class StringGenerator {

	public static final String DEFAULT_PATTERN = "[a-zA-Z_0-9]{10}";
	private final Generex generex;
	private final String pattern;

	private StringGenerator(String pattern) {
		this.pattern = pattern;
		generex = new Generex(pattern);
	}

	public void setSeed(long seed) {
		generex.setSeed(seed);
	}

	public String generateString() {
		return generex.random();
	}

	public static StringGenerator newStringGenerator(String pattern) {
		if (pattern == null) {
			return build(DEFAULT_PATTERN);
		} else {
			return build(pattern);
		}
	}

	public String getPattern() {
		return pattern;
	}

	private static StringGenerator build(String pattern) {
		return new StringGenerator(pattern);
	}
}
