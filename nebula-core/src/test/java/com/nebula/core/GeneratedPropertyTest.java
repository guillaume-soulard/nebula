package com.nebula.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneratedPropertyTest {

	@Test
	public void equals_should_assert_properties_are_equals() {

		// GIVEN
		GeneratedProperty generatedProperty1 = new GeneratedProperty("property", new GeneratedObject("value"), null);
		GeneratedProperty generatedProperty2 = new GeneratedProperty("property", new GeneratedObject("value"), null);

		// WHEN
		boolean result = generatedProperty1.equals(generatedProperty2);

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void equals_should_assert_properties_are_not_equals_because_property_names_are_differents() {

		// GIVEN
		GeneratedProperty generatedProperty1 = new GeneratedProperty("property1", new GeneratedObject("value"), null);
		GeneratedProperty generatedProperty2 = new GeneratedProperty("property2", new GeneratedObject("value"), null);

		// WHEN
		boolean result = generatedProperty1.equals(generatedProperty2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void equals_should_assert_properties_are_not_equals_because_property_values_are_differents() {

		// GIVEN
		GeneratedProperty generatedProperty1 = new GeneratedProperty("property", new GeneratedObject("value1"), null);
		GeneratedProperty generatedProperty2 = new GeneratedProperty("property", new GeneratedObject("value2"), null);

		// WHEN
		boolean result = generatedProperty1.equals(generatedProperty2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void equals_should_assert_properties_are_not_equals_because_one_is_null() {

		// GIVEN
		GeneratedProperty generatedProperty1 = new GeneratedProperty("property", new GeneratedObject("value1"), null);
		GeneratedProperty generatedProperty2 = null;

		// WHEN
		boolean result = generatedProperty1.equals(generatedProperty2);

		// THEN
		assertThat(result).isFalse();
	}
}
