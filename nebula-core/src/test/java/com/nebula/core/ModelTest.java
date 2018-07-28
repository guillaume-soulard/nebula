package com.nebula.core;

import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {

	@Test
	void newEntity_should_add_given_type_in_model() {
		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		Entity entity = model.newEntity("test", 1);

		// THEN
		assertThat(model.getEntities()).containsOnly(entity);
	}

	@Test
	void newEntity_with_no_amount_should_add_given_type_in_model() {
		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		Entity entity = model.newEntity("test");

		// THEN
		assertThat(model.getEntities()).containsOnly(entity);
	}

	@Test
	void generateAll_should_generate_10_objects() {

		// GIVEN
		long seed = 1L;
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = model.newEntity("test", 10);
		entity.addProperty("number", NebulaGenerators.random(), NebulaTypes.number().range());

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateEntitiesObjectsAll(seed);

		// THEN
		assertThat(result).containsOnlyKeys(entity);
		assertThat(result.get(entity)).hasSize(10);
	}

	@Test
	void generateEntity_should_generate_10_objects() {

		// GIVEN
		long seed = 1L;
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = model.newEntity("test", 10);
		entity.addProperty("number", NebulaGenerators.random(), NebulaTypes.number().range());

		// WHEN
		List<GeneratedObject> result = model.generateEntityObjects(entity, seed);

		// THEN
		assertThat(result).hasSize(10);
	}

	@Test
	void generateAll_should_return_only_numbers_between_minus2_and_2_over_100_generated_entities() {

		// GIVEN
		long seed = 1L;
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 100;
		Entity entity = model.newEntity("test", amount);
		String propertyName = "number";
		entity.addProperty(propertyName, NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateEntitiesObjectsAll(seed);

		// THEN
		assertThat(result.get(entity)).hasSize(amount);
		assertThat(extractValuesForProperty(propertyName, result.get(entity))).containsOnly(BigDecimal.valueOf(-2),
				BigDecimal.valueOf(-1), BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2));
	}

	@Test
	void iterator_should_return_a_new_iterator() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		entity.addProperty("number", NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));
		long seed = 1L;

		// WHEN
		GeneratedObjectIterator result = model.iterator("test", seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void iterator_should_get_same_objects_than_all_entities() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		entity.addProperty("number", NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));
		long seed = 1L;
		List<GeneratedObject> allObjects = model.generateEntityObjects(entity, seed);

		// WHEN
		GeneratedObjectIterator result = model.iterator("test", seed);

		// THEN
		assertThat(result).hasSameElementsAs(allObjects);
	}

	@Test
	void getEntityByName_should_return_entity() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		String entityName = "test";
		Entity entity = model.newEntity(entityName, amount);

		// WHEN
		Entity result = model.getEntityByName(entityName);

		// THEN
		assertThat(result).isEqualTo(entity);
	}

	@Test
	void getEntityByName_should_throw_exception_when_unexisting_entity_is_passed() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		model.newEntity("test", amount);

		// WHEN
		catchException(model).getEntityByName("unexisting");

		// THEN
		assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unexisting' not exists in model");
	}

	@Test
	void generateEntity_should_generate_entity_for_index_0() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		long seed = 1L;
		long entityIndex = 0L;

		// WHEN
		GeneratedObject result = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void generateEntity_should_generate_10_different_objects() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		long seed = 1L;

		// WHEN
		List<GeneratedObject> result = model.generateEntityObjects(entity, seed);

		// THEN
		assertThat(result).doesNotHaveDuplicates();
	}

	@Test
	void generateEntity_should_generate_the_same_entity_for_index_0() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		long seed = 1L;
		long entityIndex = 0L;

		// WHEN
		GeneratedObject object1 = model.generateEntityObject(entity, entityIndex, seed);
		GeneratedObject object2 = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(object1).isEqualTo(object2);
	}

	@Test
	void generateEntity_should_generate_the_same_entity_for_index_0_comparing_to_generate_all_entities() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		long seed = 1L;
		long entityIndex = 0L;
		GeneratedObject generatedObjectAtIndex0 = model.generateEntityObjects(entity, seed).get(0);

		// WHEN
		GeneratedObject result = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(result).isEqualTo(generatedObjectAtIndex0);
	}

	@Test
	void removeEntity_should_remove_all_entity_from_list() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		String entityName = "entity 1";
		model.newEntity(entityName);

		// WHEN
		model.removeEntity(entityName);

		// THEN
		assertThat(model.getEntities()).isEmpty();
	}

	@Test
	void removeEntity_should_remove_entity_from_list() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		String entityName = "to remove";
		model.newEntity(entityName);
		String anotherEntity = "another entity";
		model.newEntity(anotherEntity);

		// WHEN
		model.removeEntity(entityName);

		// THEN
		assertThat(model.getEntities()).extracting("name").containsOnly(anotherEntity);
	}

	@Test
	void removeEntity_should_throw_exception_when_entity_to_remove_not_exists() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		model.newEntity("entity");

		// WHEN
		catchException(model).removeEntity("un existing entity");

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'un existing entity' not exists in model");
	}

	@Test
	void newStaticEntity_should_create_a_new_static_entity_in_model() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();

		// WHEN
		model.newStaticEntity("test")
				.newRecord()
					.addProperty("field 1", "value")
					.addProperty("field 2", false)
					.addProperty("field 3", BigDecimal.ONE)
					.addProperty("field 4", new DateTime(0L))
				.newRecord()
					.addProperty("field 1", "value")
					.addProperty("field 2", false)
					.addProperty("field 3", BigDecimal.ONE)
					.addProperty("field 4", new DateTime(0L))
				.buildEntity();

		// THEN
		Entity test = model.getEntityByName("test");
		assertThat(test.getProperties()).hasSize(4);
		assertThat(test.getAmount()).isEqualTo(2);
	}

	@Test
	void generateEntityObjects_should_return_2_generated_objects_with_same_property_value_as_static_entity() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		model.newStaticEntity("test")
				.newRecord()
					.addProperty("field 1", "value")
					.addProperty("field 2", false)
					.addProperty("field 3", BigDecimal.ONE)
					.addProperty("field 4", new DateTime(0L))
				.newRecord()
					.addProperty("field 1", "value 2")
					.addProperty("field 2", true)
					.addProperty("field 3", BigDecimal.TEN)
					.addProperty("field 4", new DateTime(10L))
				.buildEntity();

		// WHEN
		List<GeneratedObject> result = model.generateEntityObjects(model.getEntityByName("test"), 0L);

		// THEN
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getGeneratedPropertyValue("field 1")).isEqualTo(new GeneratedObject("value"));
		assertThat(result.get(0).getGeneratedPropertyValue("field 2")).isEqualTo(new GeneratedObject(false));
		assertThat(result.get(0).getGeneratedPropertyValue("field 3")).isEqualTo(new GeneratedObject(BigDecimal.ONE));
		assertThat(result.get(0).getGeneratedPropertyValue("field 4")).isEqualTo(new GeneratedObject(new DateTime(0L)));


		assertThat(result.get(1).getGeneratedPropertyValue("field 1")).isEqualTo(new GeneratedObject("value 2"));
		assertThat(result.get(1).getGeneratedPropertyValue("field 2")).isEqualTo(new GeneratedObject(true));
		assertThat(result.get(1).getGeneratedPropertyValue("field 3")).isEqualTo(new GeneratedObject(BigDecimal.TEN));
		assertThat(result.get(1).getGeneratedPropertyValue("field 4")).isEqualTo(new GeneratedObject(new DateTime(10L)));
	}

	private List<Object> extractValuesForProperty(String propertyName, List<GeneratedObject> generatedObjects) {
		List<Object> values = new ArrayList<>();
		for (GeneratedObject generatedObject : generatedObjects) {
            values.addAll(generatedObject.getGeneratedProperties().stream().filter(generatedProperty -> generatedProperty.getPropertyName().equals(propertyName)).map(generatedProperty -> generatedProperty.getPropertyValue().getObject()).collect(Collectors.toList()));
		}
		return values;
	}
}
