package com.nebula.core;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EntityTest {

	@Test
    @DisplayName("addProperty should add new property in entity properties")
	void addProperty_should_add_new_property_in_entity_properties() {
		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		String propertyName = "name";
		RandomTypeBuilder propertyType = NebulaTypes.number().range();
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();

		// WHEN
		entity.addProperty(propertyName, propertyGenerator, propertyType);

		// THEN
		assertThat(entity.getProperties()).hasSize(1);
		assertThat(entity.getProperties().get(0).getName()).isEqualTo(propertyName);
		assertThat(entity.getProperties().get(0).getType()).isInstanceOf(Type.class);
		assertThat(entity.getProperties().get(0).getGenerator()).isInstanceOf(Generator.class);
	}

	@Test
    @DisplayName("addProperty should throw exception when duplicate property name is added")
	void addProperty_should_throw_exception_when_duplicate_property_name_is_added() {

		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		RandomTypeBuilder propertyType = NebulaTypes.number().range();
		String propertyName = "property name test";
		entity.addProperty(propertyName, propertyGenerator, propertyType);

		// WHEN
		catchException(entity).addProperty(propertyName, propertyGenerator, propertyType);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("duplicate property 'property name test' in entity 'test'");
	}

	@Test
    @DisplayName("generateObject should generate a non null object")
	void generateObject_should_generate_a_non_null_object() {

		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);

		// WHEN
        GeneratedObject result = entity.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return the correct amount of properties")
	void generateObject_should_return_the_correct_amount_of_properties() {

		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		String propertyName = "property";
		entity.addProperty(propertyName, NebulaGenerators.random(), NebulaTypes.number().range());
		long entityIndex = 0L;
		entity.init(new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = entity.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(1);
	}

	@Test
    @DisplayName("generateObject should return a generated object with one generated property equal to 1")
	void generateObject_should_return_a_generated_object_with_one_generated_property_equal_to_1() {

		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		String propertyName = "property";
		entity.addProperty(propertyName, NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE));
		long entityIndex = 0L;
		entity.init(new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = entity.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(1);
		assertThat(result.getGeneratedProperties().get(0).getPropertyName()).isEqualTo(propertyName);
		assertThat(result.getGeneratedProperties().get(0).getPropertyValue().getObject()).isEqualTo(BigDecimal.ONE);
	}

	@Test
    @DisplayName("generateObject should return a generated object with two properties first equal to 1 and second equal to 5")
	void generateObject_should_return_a_generated_object_with_two_properties_first_equal_to_1_and_second_equal_to_5() {

		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		String property1Name = "property1";
		String property2Name = "property2";
		entity.addProperty(property1Name, NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE));
		entity.addProperty(property2Name, NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(5)).withMax(BigDecimal.valueOf(5)));
		long entityIndex = 0L;
		entity.init(new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10));

		// WHEN
        GeneratedObject result = entity.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(2);
		assertThat(result.getGeneratedProperties().get(0).getPropertyName()).isEqualTo(property1Name);
		assertThat(result.getGeneratedProperties().get(0).getPropertyValue().getObject()).isEqualTo(BigDecimal.ONE);
		assertThat(result.getGeneratedProperties().get(1).getPropertyName()).isEqualTo(property2Name);
		assertThat(result.getGeneratedProperties().get(1).getPropertyValue().getObject())
				.isEqualTo(BigDecimal.valueOf(5));
	}

	@Test
    @DisplayName("init should init property generators")
	void init_should_init_property_generators() {

		// GIVEN
		PropertyBuilder propertyBuilder = mock(PropertyBuilder.class);
		Property property = mock(Property.class);
		Generator generator = mock(Generator.class);
		when(property.getType()).thenReturn(mock(Type.class));
		when(property.getGenerator()).thenReturn(generator);
		when(propertyBuilder.newProperty(any(Model.class), anyString(), any(RandomTypeBuilder.class), any(GeneratorBuilder.class)))
				.thenReturn(property);
		when(property.getName()).thenReturn("name");
		Entity entity = new Entity(ModelBuilder.newEmptyModel().build(), "test", 1L, propertyBuilder);
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		when(nebulaRandom.getSeed()).thenReturn(0L);
		entity.addProperty("name", null, null);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, null, entityIndex, 1, 10);

		// WHEN
		entity.init(context);

		// THEN
		verify(generator, times(1)).init(any(GenerationContext.class));
	}

	@Test
    @DisplayName("init should init property type")
	void init_should_init_property_type() {

		// GIVEN
		PropertyBuilder propertyBuilder = mock(PropertyBuilder.class);
		Property property = mock(Property.class);
		Type type = mock(Type.class);
		when(property.getGenerator()).thenReturn(mock(Generator.class));
		when(property.getType()).thenReturn(type);
		when(property.getName()).thenReturn("name");
		when(propertyBuilder.newProperty(any(Model.class), anyString(), any(RandomTypeBuilder.class), any(GeneratorBuilder.class)))
				.thenReturn(property);
		Entity entity = new Entity(ModelBuilder.newEmptyModel().build(), "test", 1L, propertyBuilder);
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		entity.addProperty(null, null, null);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, null, entityIndex, 1, 10);

		// WHEN
		entity.init(context);

		// THEN
		verify(type, times(1)).init(any(GenerationContext.class));
	}

	@Test
    @DisplayName("setPropertyBuilder should set propertyBuilder filed in given entity")
	void setPropertyBuilder_should_set_propertyBuilder_filed_in_given_entity() {

		// GIVEN
		Entity entity = ModelBuilder.newEmptyModel().build().newEntity("test", 1);
		PropertyBuilder propertyBuilder = new PropertyBuilder();

		// WHEN
		entity.setPropertyBuilder(propertyBuilder);

		// THEN
		assertThat(entity).hasFieldOrPropertyWithValue("propertyBuilder", propertyBuilder);
	}
}
