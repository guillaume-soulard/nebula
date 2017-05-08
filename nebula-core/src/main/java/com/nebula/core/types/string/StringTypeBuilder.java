package com.nebula.core.types.string;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class StringTypeBuilder implements TypeBuilder {

	public static final String DEFAULT_ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

	private int minLength = 10;
	private int maxLength = 10;
	private String allowedChars = DEFAULT_ALLOWED_CHARS;

	@Override
	public Type build() throws NebulaException {
		if (minLength > maxLength) {
			throw new NebulaException("maxLength must be greater than minLength");
		}
		return new StringType(minLength, maxLength, allowedChars);
	}

	public StringTypeBuilder withMinLength(int value) {
		minLength = value;
		return this;
	}

	public StringTypeBuilder withMaxLength(int value) {
		maxLength = value;
		return this;
	}

	public StringTypeBuilder withAllowedChars(String value) {
		if (value == null) {
			throw new NebulaException("allowed chars is null");
		}
		allowedChars = value;
		return this;
	}
}
