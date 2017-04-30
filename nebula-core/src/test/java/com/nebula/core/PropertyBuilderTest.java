package com.nebula.core;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;

public class PropertyBuilderTest {

	@Test
	public void newProperty_should_throw_NebulaException_when_property_name_is_null() throws NebulaException {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = null;
		Type propertyType = NebulaTypes.integer();
		Generator propertyGenerator = NebulaGenerators.random();

		// WHEN
		catchException(propertyBuilder).newProperty(propertyName, propertyType, propertyGenerator);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("property name is null");
	}

	@Test
	public void newProperty_should_throw_NebulaException_when_property_type_is_null() throws NebulaException {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		Type propertyType = null;
		Generator propertyGenerator = NebulaGenerators.random();

		// WHEN
		catchException(propertyBuilder).newProperty(propertyName, propertyType, propertyGenerator);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("property type is null");
	}

	@Test
	public void newProperty_should_throw_NebulaException_when_property_generator_is_null() throws NebulaException {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		Type propertyType = NebulaTypes.integer();
		Generator propertyGenerator = null;

		// WHEN
		catchException(propertyBuilder).newProperty(propertyName, propertyType, propertyGenerator);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("property generator is null");
	}
}
