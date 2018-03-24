package com.nebula.core;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.TypeBuilder;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class PropertyBuilderTest {

	@Test
	public void newProperty_should_throw_NebulaException_when_property_name_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = null;
		TypeBuilder propertyType = NebulaTypes.number().range();
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, null, propertyType, propertyGenerator);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property name is null");
	}

	@Test
	public void newProperty_should_throw_NebulaException_when_property_type_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		TypeBuilder propertyType = null;
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, propertyName, null, propertyGenerator);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property type is null");
	}

	@Test
	public void newProperty_should_throw_NebulaException_when_property_generator_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		TypeBuilder propertyType = NebulaTypes.number().range();
		GeneratorBuilder propertyGenerator = null;
		Model model = ModelBuilder.newModel().build();

		// WHEN
		catchException(propertyBuilder).newProperty(model, propertyName, propertyType, null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("property generator is null");
	}
}
