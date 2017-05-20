package com.nebula.core.types.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringGeneratorTest {

	@Test
	public void newStringGenerator_should_return_a_new_instance_of_StringGenerator() {

		// GIVEN
		String pattern = "test";

		// WHEN
		StringGenerator result = StringGenerator.newStringGenerator(pattern);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void newStringGenerator_should_set_default_pattern_when_null_is_passed() {

		// GIVEN
		String expectedDefaultPattern = "[a-zA-Z_0-9]{10}";

		// WHEN
		StringGenerator result = StringGenerator.newStringGenerator(null);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("pattern", expectedDefaultPattern);
	}

	@Test
	public void generateString_should_return_test() {

		// GIVEN
		String expectedString = "test";
		StringGenerator generator = StringGenerator.newStringGenerator(expectedString);

		// WHEN
		String result = generator.generateString();

		// THEN
		assertThat(result).isEqualTo(expectedString);
	}

	@Test
	public void generateString_should_return_a_string_matching_pattern() {

		// GIVEN
		String pattern = "[a-z]{10}";
		StringGenerator generator = StringGenerator.newStringGenerator(pattern);

		// WHEN
		String result = generator.generateString();

		// THEN
		assertThat(result).matches(pattern);
	}
}
