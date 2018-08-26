package com.nebula.core.types.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringGeneratorTest {

	@Test
    @DisplayName("newStringGenerator should return a new instance of StringGenerator")
	void newStringGenerator_should_return_a_new_instance_of_StringGenerator() {

		// GIVEN
		String pattern = "test";

		// WHEN
		StringGenerator result = StringGenerator.newStringGenerator(pattern);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("newStringGenerator should set default pattern when null is passed")
	void newStringGenerator_should_set_default_pattern_when_null_is_passed() {

		// GIVEN
		String expectedDefaultPattern = "[a-zA-Z_0-9]{10}";

		// WHEN
		StringGenerator result = StringGenerator.newStringGenerator(null);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("pattern", expectedDefaultPattern);
	}

	@Test
    @DisplayName("generateString should return test")
	void generateString_should_return_test() {

		// GIVEN
		String expectedString = "test";
		StringGenerator generator = StringGenerator.newStringGenerator(expectedString);

		// WHEN
		String result = generator.generateString();

		// THEN
		assertThat(result).isEqualTo(expectedString);
	}

	@Test
    @DisplayName("generateString should return a string matching pattern")
	void generateString_should_return_a_string_matching_pattern() {

		// GIVEN
		String pattern = "[a-z]{10}";
		StringGenerator generator = StringGenerator.newStringGenerator(pattern);

		// WHEN
		String result = generator.generateString();

		// THEN
		assertThat(result).matches(pattern);
	}
}
