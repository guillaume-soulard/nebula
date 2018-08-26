package com.nebula.core;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.TypeBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PropertyBuilderTest {

	@Test
    @DisplayName("newProperty should throw NebulaException when property name is null")
	void newProperty_should_throw_NebulaException_when_property_name_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		TypeBuilder propertyType = NebulaTypes.number().range();
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN

		// THEN
        assertThatThrownBy(() -> propertyBuilder.newProperty(model, null, propertyType, propertyGenerator))
                .isInstanceOf(NebulaException.class)
                .hasMessage("property name is null");
	}

	@Test
    @DisplayName("newProperty should throw NebulaException when property type is null")
	void newProperty_should_throw_NebulaException_when_property_type_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN

		// THEN
        assertThatThrownBy(() -> propertyBuilder.newProperty(model, propertyName, null, propertyGenerator))
                .isInstanceOf(NebulaException.class)
                .hasMessage("property type is null");
	}

	@Test
    @DisplayName("newProperty should throw NebulaException when property generator is null")
	void newProperty_should_throw_NebulaException_when_property_generator_is_null() {
		// GIVEN
		PropertyBuilder propertyBuilder = new PropertyBuilder();
		String propertyName = "name";
		TypeBuilder propertyType = NebulaTypes.number().range();
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN

		// THEN
        assertThatThrownBy(() -> propertyBuilder.newProperty(model, propertyName, propertyType, null))
                .isInstanceOf(NebulaException.class)
                .hasMessage("property generator is null");
	}
}
