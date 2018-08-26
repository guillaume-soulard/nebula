package com.nebula.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneratedObjectTest {

	@Test
    @DisplayName("getObject should return object passed in constructor")
	void getObject_should_return_object_passed_in_constructor() {
		// GIVEN
		Object object = "test";
		GeneratedObject generatedObject = new GeneratedObject(object);

		// WHEN
		Object result = generatedObject.getObject();

		// THEN
		assertThat(result).isEqualTo(object);
	}

	@Test
    @DisplayName("getGeneratedProperties should return list passed in constructor")
	void getGeneratedProperties_should_return_list_passed_in_constructor() {

		// GIVEN
		List<GeneratedProperty> generatedProperties = new ArrayList<>();
		GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

		// WHEN
		List<GeneratedProperty> result = generatedObject.getGeneratedProperties();

		// THEN
		assertThat(result).isEqualTo(generatedProperties);
	}

	@Test
    @DisplayName("equals should assert objects are equals comparing object")
	void equals_should_assert_objects_are_equals_comparing_object() {

		// GIVEN
		GeneratedObject object1 = new GeneratedObject("test");
		GeneratedObject object2 = new GeneratedObject("test");

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isTrue();
	}

	@Test
    @DisplayName("equals should assert objects are not equals comparing object")
	void equals_should_assert_objects_are_not_equals_comparing_object() {

		// GIVEN
		GeneratedObject object1 = new GeneratedObject("qwerty");
		GeneratedObject object2 = new GeneratedObject("azerty");

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("equals should assert objects are equals comparing properties")
	void equals_should_assert_objects_are_equals_comparing_properties() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Collections.singletonList(new GeneratedProperty("test", new GeneratedObject("value"), null));
		List<GeneratedProperty> propertiesOfObject2 = Collections.singletonList(new GeneratedProperty("test", new GeneratedObject("value"), null));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isTrue();
	}

	@Test
    @DisplayName("equals should assert objects are not equals comparing properties names")
	void equals_should_assert_objects_are_not_equals_comparing_properties_names() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Collections.singletonList(new GeneratedProperty("property1", new GeneratedObject("value"), null));
		List<GeneratedProperty> propertiesOfObject2 = Collections.singletonList(new GeneratedProperty("property2", new GeneratedObject("value"), null));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("equals should assert objects are not equals comparing properties values")
	void equals_should_assert_objects_are_not_equals_comparing_properties_values() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Collections.singletonList(new GeneratedProperty("property", new GeneratedObject("value1"), null));
		List<GeneratedProperty> propertiesOfObject2 = Collections.singletonList(new GeneratedProperty("property", new GeneratedObject("value2"), null));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);
		GeneratedObject object2 = new GeneratedObject(propertiesOfObject2);

		// WHEN
		boolean result = object1.equals(object2);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("equals should assert objects are not equals to null")
	void equals_should_assert_objects_are_not_equals_to_null() {

		// GIVEN
		List<GeneratedProperty> propertiesOfObject1 = Collections.singletonList(new GeneratedProperty("property", new GeneratedObject("value1"), null));
		GeneratedObject object1 = new GeneratedObject(propertiesOfObject1);

		// WHEN
		boolean result = object1.equals(null);

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("getGeneratedPropertyValue should return the proeprty value")
	void getGeneratedPropertyValue_should_return_the_proeprty_value() {

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
    @DisplayName("getGeneratedPropertyValue should throw exception when propertyName is unknown")
	void getGeneratedPropertyValue_should_throw_exception_when_propertyName_is_unknown() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		GeneratedObject value = new GeneratedObject("value");
		GeneratedProperty generatedProperty = new GeneratedProperty("test", value, null);
		properties.add(generatedProperty);
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN

		// THEN
        assertThatThrownBy(() -> generatedObject.getGeneratedPropertyValue("unexisting"))
                .isInstanceOf(NebulaException.class)
                .hasMessage("Property 'unexisting' is undefined");
	}

	@Test
    @DisplayName("toString should be value")
	void toString_should_be_value() {

		// GIVEN
		GeneratedObject generatedObject;

		// WHEN
		generatedObject = new GeneratedObject("value");

		// THEN
		assertThat(generatedObject).hasToString("\"value\"");
	}

	@Test
    @DisplayName("toString should be 10")
	void toString_should_be_10() {

		// GIVEN
		GeneratedObject generatedObject;

		// WHEN
		generatedObject = new GeneratedObject(BigDecimal.TEN);

		// THEN
		assertThat(generatedObject).hasToString("10");
	}

	@Test
    @DisplayName("toString should be empty array")
	void toString_should_be_empty_array() {

		// GIVEN
		GeneratedObject generatedObject;
		List<String> list = new ArrayList<>();

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[]");
	}

	@Test
    @DisplayName("toString should be array of 3 strings")
	void toString_should_be_array_of_3_strings() {

		// GIVEN
		GeneratedObject generatedObject;
		List<String> list = Arrays.asList("a", "b", "c");

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[\"a\",\"b\",\"c\"]");
	}

	@Test
    @DisplayName("toString should be array of 3 numbers")
	void toString_should_be_array_of_3_numbers() {

		// GIVEN
		GeneratedObject generatedObject;
		List<BigDecimal> list = Arrays.asList(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);

		// WHEN
		generatedObject = new GeneratedObject(list);

		// THEN
		assertThat(generatedObject).hasToString("[0,1,10]");
	}

	@Test
    @DisplayName("toString should return two properties")
	void toString_should_return_two_properties() {

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

	@Test
    @DisplayName("getValueByPath should return the value when object is final")
	void getValueByPath_should_return_the_value_when_object_is_final() {

		// GIVEN
		GeneratedObject generatedObject = new GeneratedObject("test");

		// WHEN
		Object result = generatedObject.getValueByPath("");

		// THEN
		assertThat(result).isEqualTo("test");
	}

	@Test
    @DisplayName("getValueByPath should return the value of an existing property")
	void getValueByPath_should_return_the_value_of_an_existing_property() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		properties.add(new GeneratedProperty("customProperty", new GeneratedObject("value"), null));
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		Object result = generatedObject.getValueByPath("customProperty");

		// THEN
		assertThat(result).isEqualTo("value");
	}

	@Test
    @DisplayName("getValueByPath should throw an exception when property not exists")
	void getValueByPath_should_throw_an_exception_when_property_not_exists() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		properties.add(new GeneratedProperty("customProperty", new GeneratedObject("value"), null));
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN

		// THEN
        assertThatThrownBy(() -> generatedObject.getValueByPath("nonExisting"))
                .isInstanceOf(NebulaException.class)
                .hasMessage("Property 'nonExisting' not exists");
	}

	@Test
    @DisplayName("getValueByPath should return the value of an existing property at level 2")
	void getValueByPath_should_return_the_value_of_an_existing_property_at_level_2() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		List<GeneratedProperty> level2 = new ArrayList<>();
		level2.add(new GeneratedProperty("level2", new GeneratedObject("level2 value"), null));
		properties.add(new GeneratedProperty("customProperty", new GeneratedObject(level2), null));
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		Object result = generatedObject.getValueByPath("customProperty.level2");

		// THEN
		assertThat(result).isEqualTo("level2 value");
	}

	@Test
    @DisplayName("getValueByPath should return the value of an existing property with complex name")
	void getValueByPath_should_return_the_value_of_an_existing_property_with_complex_name() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		properties.add(new GeneratedProperty("custom property\"name\"",new GeneratedObject("test"), null));
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		Object result = generatedObject.getValueByPath("custom property\"name\"");

		// THEN
		assertThat(result).isEqualTo("test");
	}

	@Test
    @DisplayName("getValueByPath should return the value of an existing property with complex name at level 2")
	void getValueByPath_should_return_the_value_of_an_existing_property_with_complex_name_at_level_2() {

		// GIVEN
		List<GeneratedProperty> properties = new ArrayList<>();
		List<GeneratedProperty> level2 = new ArrayList<>();
		level2.add(new GeneratedProperty("at level 2", new GeneratedObject("level2 value"), null));
		properties.add(new GeneratedProperty("custom property \"name\"", new GeneratedObject(level2), null));
		GeneratedObject generatedObject = new GeneratedObject(properties);

		// WHEN
		Object result = generatedObject.getValueByPath("custom property \"name\".at level 2");

		// THEN
		assertThat(result).isEqualTo("level2 value");
	}
}
