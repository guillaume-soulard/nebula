package com.nebula.core.types.string;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class StringTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_StringType() {

		// GIVEN
		TypeBuilder builder = new StringTypeBuilder();

		// WHEN
		Type result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(StringType.class);
	}

	@Test
	public void newStringTypeBuilder_should_set_10_as_default_minLength() {

		// GIVEN
		StringTypeBuilder builder = null;

		// WHEN
		builder = new StringTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("minLength", 10);
	}

	@Test
	public void withMinLength_should_set_5_in_builder_s_properties() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();

		// WHEN
		StringTypeBuilder result = builder.withMinLength(5);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minLength", 5);
	}

	@Test
	public void newStringTypeBuilder_should_set_10_as_default_maxLength() {

		// GIVEN
		StringTypeBuilder builder = null;

		// WHEN
		builder = new StringTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("maxLength", 10);
	}

	@Test
	public void withMaxLength_should_set_15_in_builder_s_properties() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();

		// WHEN
		StringTypeBuilder result = builder.withMaxLength(15);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxLength", 15);
	}

	@Test
	public void build_should_throw_NebulaException_when_max_is_less_than_min() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();

		// WHEN
		catchException(builder.withMaxLength(5)).build();

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("maxLength must be greater than minLength");
	}

	@Test
	public void build_should_throw_NebulaException_when_min_is_greater_than_max() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();

		// WHEN
		catchException(builder.withMinLength(15)).build();

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("maxLength must be greater than minLength");
	}

	@Test
	public void withMinLength_and_withMaxLength_should_not_throw_exception_when_both_set_the_same_value() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		int value = 15;

		// WHEN
		builder.withMinLength(value).withMaxLength(value).build();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("minLength", value).hasFieldOrPropertyWithValue("maxLength",
				value);
	}

	@Test
	public void withAllowedChars_should_set_allowed_chars_in_builder() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		String allowedChars = "abcdefghijklmnopqrstuvwxyz";

		// WHEN
		StringTypeBuilder result = builder.withAllowedChars(allowedChars);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("allowedChars", allowedChars);
	}

	@Test
	public void newStringTypeBuilder_should_set_default_alowedChars() {

		// GIVEN
		StringTypeBuilder builder = null;
		String defaultAlowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

		// WHEN
		builder = new StringTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("allowedChars", defaultAlowedChars);
	}

	@Test
	public void withAllowedChars_should_throw_exception_when_allowed_chars_is_null() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		String allowedChars = null;

		// WHEN
		catchException(builder).withAllowedChars(allowedChars);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("allowed chars is null");
	}
}
