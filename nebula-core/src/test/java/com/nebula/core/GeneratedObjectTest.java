package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GeneratedObjectTest {

	@Test
	public void getObject_should_return_object_passed_in_constructor() {
		// GIVEN
		Object object = new String("test");
		GeneratedObject generatedObject = new GeneratedObject(object);

		// WHEN
		Object result = generatedObject.getObject();

		// THEN
		assertThat(result).isEqualTo(object);
	}

	@Test
	public void getGeneratedProperties_should_return_list_passed_in_constructor() {

		// GIVEN
		List<GeneratedProperty> generatedProperties = new ArrayList<GeneratedProperty>();
		GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

		// WHEN
		List<GeneratedProperty> result = generatedObject.getGeneratedProperties();

		// THEN
		assertThat(result).isEqualTo(generatedProperties);
	}

	@Test
	public void equals_should_assert_objects_are_equals_comparing_object() {

		// GIVEN
		GeneratedObject object1 = new GeneratedObject("test");
		GeneratedObject object2 = new GeneratedObject("test");

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void equals_should_assert_objects_are_not_equals_comparing_object() {

		// GIVEN
		GeneratedObject object1 = new GeneratedObject("qwerty");
		GeneratedObject object2 = new GeneratedObject("azerty");

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void equals_should_assert_objects_are_equals_comparing_properties() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Arrays
				.asList(new GeneratedProperty("test", new GeneratedObject("value")));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("test", new GeneratedObject("value")));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void equals_should_assert_objects_are_not_equals_comparing_properties_names() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Arrays
				.asList(new GeneratedProperty("property1", new GeneratedObject("value")));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("property2", new GeneratedObject("value")));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void equals_should_assert_objects_are_not_equals_comparing_properties_values() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Arrays
				.asList(new GeneratedProperty("property", new GeneratedObject("value1")));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("property", new GeneratedObject("value2")));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void equals_should_assert_objects_are_not_equals_to_null() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Arrays
				.asList(new GeneratedProperty("property", new GeneratedObject("value1")));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = null;

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}
}
