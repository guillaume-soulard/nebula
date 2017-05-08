package com.nebula.core.types.string;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class StringType implements Type {

	private NebulaRandom nebulaRandom;
	private int minLength;
	private int maxLength;
	private String allowedChars;

	public StringType(int minLength, int maxLength, String allowedChars) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.allowedChars = allowedChars;
	}

	@Override
	public void init(NebulaRandom nebulaRandom) throws NebulaException {
		this.nebulaRandom = nebulaRandom;
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {
		StringBuilder builder = new StringBuilder();
		Long stringLength = nebulaRandom.randomBetween(minLength, maxLength);
		for (int charIndex = 1; charIndex <= stringLength; charIndex++) {
			builder.append(allowedChars
					.charAt(Integer.valueOf(Long.toString(nebulaRandom.randomBetween(0, allowedChars.length() - 1)))));
		}
		return new GeneratedObject(builder.toString());
	}

	@Override
	public Long getMinRange() {
		return (long) minLength;
	}

	@Override
	public Long getMaxRange() {
		return (long) maxLength;
	}

}
