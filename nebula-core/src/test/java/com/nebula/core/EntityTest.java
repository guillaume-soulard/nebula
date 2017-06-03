package com.nebula.core;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.generators.random.RandomGenerator;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import com.nebula.core.types.number.NumberRangeType;

public class EntityTest {

	@Test
	public void addProperty_should_add_new_property_in_entity_properties() {
		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);
		String propertyName = "name";
		TypeBuilder propertyType = NebulaGenerationTypes.number().range();
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();

		// WHEN
		entity.addProperty(propertyName, propertyGenerator, propertyType);

		// THEN
		assertThat(entity.getProperties()).hasSize(1);
		assertThat(entity.getProperties().get(0).getName()).isEqualTo(propertyName);
		assertThat(entity.getProperties().get(0).getType()).isInstanceOf(NumberRangeType.class);
		assertThat(entity.getProperties().get(0).getGenerator()).isInstanceOf(RandomGenerator.class);
	}

	@Test
	public void addProperty_should_throw_exception_when_duplicate_property_name_is_added() {

		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);
		GeneratorBuilder propertyGenerator = NebulaGenerators.random();
		TypeBuilder propertyType = NebulaGenerationTypes.number().range();
		String propertyName = "property name test";
		entity.addProperty(propertyName, propertyGenerator, propertyType);

		// WHEN
		catchException(entity).addProperty(propertyName, propertyGenerator, propertyType);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("duplicate property 'property name test' in entity 'test'");
	}

	@Test
	public void generateObject_should_generate_a_non_null_object() {

		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);

		// WHEN
		GeneratedObject result = entity.generateObject(1l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_the_correct_amount_of_properties() {

		Entity entity = NebulaCore.newEntity("test", 1);
		String propertyName = "property";
		entity.addProperty(propertyName, NebulaGenerators.random(), NebulaGenerationTypes.number().range());
		entity.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = entity.generateObject(1l);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(1);
	}

	@Test
	public void generateObject_should_return_a_generated_object_with_one_generated_property_equal_to_1() {

		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);
		String propertyName = "property";
		entity.addProperty(propertyName, NebulaGenerators.random(),
				NebulaGenerationTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE));
		entity.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = entity.generateObject(1l);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(1);
		assertThat(result.getGeneratedProperties().get(0).getPropertyName()).isEqualTo(propertyName);
		assertThat(result.getGeneratedProperties().get(0).getPropertyValue().getObject()).isEqualTo(BigDecimal.ONE);
	}

	@Test
	public void generateObject_should_return_a_generated_object_with_two_properties_first_equal_to_1_and_second_equal_to_5() {

		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);
		String property1Name = "property1";
		String property2Name = "property2";
		entity.addProperty(property1Name, NebulaGenerators.random(),
				NebulaGenerationTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE));
		entity.addProperty(property2Name, NebulaGenerators.random(),
				NebulaGenerationTypes.number().range().withMin(BigDecimal.valueOf(5)).withMax(BigDecimal.valueOf(5)));
		entity.init(new GenerationContext(new NebulaRandom(1l), null));

		// WHEN
		GeneratedObject result = entity.generateObject(1l);

		// THEN
		assertThat(result.getGeneratedProperties()).hasSize(2);
		assertThat(result.getGeneratedProperties().get(0).getPropertyName()).isEqualTo(property1Name);
		assertThat(result.getGeneratedProperties().get(0).getPropertyValue().getObject()).isEqualTo(BigDecimal.ONE);
		assertThat(result.getGeneratedProperties().get(1).getPropertyName()).isEqualTo(property2Name);
		assertThat(result.getGeneratedProperties().get(1).getPropertyValue().getObject())
				.isEqualTo(BigDecimal.valueOf(5));
	}

	@Test
	public void init_should_init_property_generators() {

		// GIVEN
		PropertyBuilder propertyBuilder = mock(PropertyBuilder.class);
		Property property = mock(Property.class);
		Generator generator = mock(Generator.class);
		when(property.getType()).thenReturn(mock(Type.class));
		when(property.getGenerator()).thenReturn(generator);
		when(propertyBuilder.newProperty(anyString(), any(TypeBuilder.class), any(GeneratorBuilder.class)))
				.thenReturn(property);
		when(property.getName()).thenReturn("name");
		Entity entity = new Entity("test", 1l, propertyBuilder);
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		when(nebulaRandom.getSeed()).thenReturn(0l);
		entity.addProperty("name", null, null);
		GenerationContext context = new GenerationContext(nebulaRandom, null);

		// WHEN
		entity.init(context);

		// THEN
		verify(generator, times(1)).init(any(GenerationContext.class));
	}

	@Test
	public void init_should_init_property_type() {

		// GIVEN
		PropertyBuilder propertyBuilder = mock(PropertyBuilder.class);
		Property property = mock(Property.class);
		Type type = mock(Type.class);
		when(property.getGenerator()).thenReturn(mock(Generator.class));
		when(property.getType()).thenReturn(type);
		when(property.getName()).thenReturn("name");
		when(propertyBuilder.newProperty(anyString(), any(TypeBuilder.class), any(GeneratorBuilder.class)))
				.thenReturn(property);
		Entity entity = new Entity("test", 1l, propertyBuilder);
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		entity.addProperty(null, null, null);
		GenerationContext context = new GenerationContext(nebulaRandom, null);

		// WHEN
		entity.init(context);

		// THEN
		verify(type, times(1)).init(any(GenerationContext.class));
	}

	@Test
	public void setPropertyBuilder_should_set_propertyBuilder_filed_in_given_entity() {

		// GIVEN
		Entity entity = NebulaCore.newEntity("test", 1);
		PropertyBuilder propertyBuilder = new PropertyBuilder();

		// WHEN
		entity.setPropertyBuilder(propertyBuilder);

		// THEN
		assertThat(entity).hasFieldOrPropertyWithValue("propertyBuilder", propertyBuilder);
	}
}
