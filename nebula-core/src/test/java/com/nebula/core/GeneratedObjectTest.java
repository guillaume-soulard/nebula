package com.nebula.core;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

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
		List<GeneratedProperty> generatedProperties = new ArrayList<>();
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
				.asList(new GeneratedProperty("test", new GeneratedObject("value"), null));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("test", new GeneratedObject("value"), null));
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
				.asList(new GeneratedProperty("property1", new GeneratedObject("value"), null));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("property2", new GeneratedObject("value"), null));
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
				.asList(new GeneratedProperty("property", new GeneratedObject("value1"), null));
		List<GeneratedProperty> propertiesOfObject2 = Arrays
				.asList(new GeneratedProperty("property", new GeneratedObject("value2"), null));
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
				.asList(new GeneratedProperty("property", new GeneratedObject("value1"), null));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = null;

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void getGeneratedPropertyValue_should_return_the_proeprty_value() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		GeneratedObject value = new GeneratedObject("value");
		String propertyName = "test";
		GeneratedProperty generatedProperty = new GeneratedProperty(propertyName, value, null);
		properties.add(generatedProperty);
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		GeneratedObject result = generatedObject.getGeneratedPropertyValue(propertyName);

		// THEN
		assertThat(result).isEqualTo(value);
	}

	@Test
	public void getGeneratedPropertyValue_should_throw_exception_when_propertyName_is_unknown() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		GeneratedObject value = new GeneratedObject("value");
		GeneratedProperty generatedProperty = new GeneratedProperty("test", value, null);
		properties.add(generatedProperty);
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		catchException(generatedObject).getGeneratedPropertyValue("unexisting");

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("Property 'unexisting' is undefined");
	}

	@Test
	public void toString_should_be_value() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;

		// WHEN
		generatedObject = new GeneratedObject("value");

		// THEN
		assertThat(generatedObject).hasToString("\"value\"");
	}

	@Test
	public void toString_should_be_10() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;

		// WHEN
		generatedObject = new GeneratedObject(BigDecimal.TEN);

		// THEN
		assertThat(generatedObject).hasToString("10");
	}

	@Test
	public void toString_should_be_empty_array() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;
		List<String> list = new ArrayList<>();

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[]");
	}

	@Test
	public void toString_should_be_array_of_3_strings() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;
		List<String> list = Arrays.asList("a", "b", "c");

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[\"a\",\"b\",\"c\"]");
	}

	@Test
	public void toString_should_be_array_of_3_numbers() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;
		List<BigDecimal> list = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[0,1,10]");
	}

	@Test
	public void toString_should_return_two_properties() throws Exception {

		// GIVEN
		GeneratedObject generatedObject;
		List<GeneratedProperty> list = new ArrayList<>();
		list.add(new GeneratedProperty("property 1", new GeneratedObject("value"), null));
        list.add(new GeneratedProperty("property 2", new GeneratedObject(BigDecimal.TEN), null));

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("{\"property 1\":\"value\",\"property 2\":10}");
	}
}
