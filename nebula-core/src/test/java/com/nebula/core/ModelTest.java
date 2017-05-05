package com.nebula.core;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ModelTest {

	@Test
	public void add_should_add_given_type_in_model() throws NebulaException {
		// GIVEN
		Entity entity = Nebula.newEntity("test", 1);
		Model model = new Model();

		// WHEN
		model.addEntity(entity);

		// THEN
		assertThat(model.getEntities()).containsOnly(entity);
	}

	@Test
	public void add_should_throw_nubula_exception_when_null_type_is_passed() throws NebulaException {
		// GIVEN
		Entity entity = null;
		Model model = new Model();

		// WHEN
		catchException(model).addEntity(entity);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("type is null");
	}

	@Test
	public void generateAll_should_generate_10_objects() throws NebulaException {

		// GIVEN
		long seed = 1l;
		Model model = new Model();
		Entity entity = Nebula.newEntity("test", 10);
		entity.addProperty("number", NebulaTypes.integer(), NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateAll(seed);

		// THEN
		assertThat(result).containsOnlyKeys(entity);
		assertThat(result.get(entity)).hasSize(10);
	}

	@Test
	public void generateEntity_should_generate_10_objects() throws NebulaException {

		// GIVEN
		long seed = 1l;
		Model model = new Model();
		Entity entity = Nebula.newEntity("test", 10);
		entity.addProperty("number", NebulaTypes.integer(), NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		List<GeneratedObject> result = model.generateEntity(entity, seed);

		// THEN
		assertThat(result).hasSize(10);
	}

	@Test
	public void generateAll_should_return_only_numbers_between_minus2_and_2_over_100_generated_entities()
			throws NebulaException {

		// GIVEN
		long seed = 1l;
		Model model = new Model();
		int amount = 100;
		Entity entity = Nebula.newEntity("test", amount);
		String propertyName = "number";
		entity.addProperty(propertyName, NebulaTypes.integer().withMin(-2).withMax(2), NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateAll(seed);

		// THEN
		assertThat(result.get(entity)).hasSize(amount);
		assertThat(extractValuesForProperty(propertyName, result.get(entity))).containsOnly(-2l, -1l, 0l, 1l, 2l);
	}

	@Test
	public void iterator_should_return_a_new_iterator() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		entity.addProperty("number", NebulaTypes.integer().withMin(-2).withMax(2), NebulaGenerators.random());
		model.addEntity(entity);
		long seed = 1l;

		// WHEN
		GeneratedObjectIterator result = model.iterator("test", seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void getEntityByName_should_return_entity() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		String entityName = "test";
		Entity entity = Nebula.newEntity(entityName, amount);
		model.addEntity(entity);

		// WHEN
		Entity result = model.getEntityByName(entityName);

		// THEN
		assertThat(result).isEqualTo(entity);
	}

	@Test
	public void getEntityByName_should_return_null() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		model.addEntity(entity);

		// WHEN
		Entity result = model.getEntityByName("unexisting");

		// THEN
		assertThat(result).isNull();
	}

	@Test
	public void generateEntity_should_generate_entity_for_index_0() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;

		// WHEN
		GeneratedObject result = model.generateEntity(entity, entityIndex, seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateEntity_should_generate_10_different_objects() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;

		// WHEN
		List<GeneratedObject> result = model.generateEntity(entity, seed);

		// THEN
		assertThat(result).doesNotHaveDuplicates();
	}

	@Test
	public void generateEntity_should_generate_the_same_entity_for_index_0() throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;

		// WHEN
		GeneratedObject object1 = model.generateEntity(entity, entityIndex, seed);
		GeneratedObject object2 = model.generateEntity(entity, entityIndex, seed);

		// THEN
		assertThat(object1).isEqualTo(object2);
	}

	@Test
	public void generateEntity_should_generate_the_same_entity_for_index_0_comparing_to_generate_all_entities()
			throws NebulaException {

		// GIVEN
		Model model = new Model();
		int amount = 10;
		Entity entity = Nebula.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;
		GeneratedObject genratedObjectAtIndex0 = model.generateEntity(entity, seed).get(0);

		// WHEN
		GeneratedObject result = model.generateEntity(entity, entityIndex, seed);

		// THEN
		assertThat(result).isEqualTo(genratedObjectAtIndex0);
	}

	private List<Object> extractValuesForProperty(String propertyName, List<GeneratedObject> generatedObjects) {
		List<Object> values = new ArrayList<Object>();
		for (GeneratedObject generatedObject : generatedObjects) {
			for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
				if (generatedProperty.getPropertyName().equals(propertyName)) {
					values.add(generatedProperty.getPropertyValue().getObject());
				}
			}
		}
		return values;
	}
}
