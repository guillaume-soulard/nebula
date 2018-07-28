package com.nebula.core;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.TypeBuilder;
import org.junit.jupiter.api.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class PropertyBuilderTest {

	@Test
	void newProperty_should_throw_NebulaException_when_property_name_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		TypeBuilder propertyType = NebulaTypes.number().range();
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, null, propertyType, propertyGenerator);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property name is null");
	}

	@Test
	void newProperty_should_throw_NebulaException_when_property_type_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, propertyName, null, propertyGenerator);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property type is null");
	}

	@Test
	void newProperty_should_throw_NebulaException_when_property_generator_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		TypeBuilder propertyType = NebulaTypes.number().range();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, propertyName, propertyType, null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property generator is null");
	}
}
