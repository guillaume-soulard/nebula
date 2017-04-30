package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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
}
