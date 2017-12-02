package com.nebula;

import com.nebula.core.*;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class ModelTest {

	@Test
	public void add_should_add_given_type_in_model() {
		// GIVEN
		Model model = new ModelBuilder().build();
		Entity entity = model.newEntity("test", 1);

		// WHEN
		model.addEntity(entity);

		// THEN
		assertThat(model.getEntities()).containsOnly(entity);
	}

	@Test
	public void add_should_throw_nebula_exception_when_null_type_is_passed() {
		// GIVEN
		Entity entity = null;
		Model model = new ModelBuilder().build();

		// WHEN
		catchException(model).addEntity(entity);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("type is null");
	}

	@Test
	public void generateAll_should_generate_10_objects() {

		// GIVEN
		long seed = 1l;
		Model model = new ModelBuilder().build();
		Entity entity = model.newEntity("test", 10);
		entity.addProperty("number", NebulaGenerators.random(), NebulaTypes.number().range());
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateEntitiesObjectsAll(seed);

		// THEN
		assertThat(result).containsOnlyKeys(entity);
		assertThat(result.get(entity)).hasSize(10);
	}

	@Test
	public void generateEntity_should_generate_10_objects() {

		// GIVEN
		long seed = 1l;
		Model model = new ModelBuilder().build();
		Entity entity = model.newEntity("test", 10);
		entity.addProperty("number", NebulaGenerators.random(), NebulaTypes.number().range());
		model.addEntity(entity);

		// WHEN
		List<GeneratedObject> result = model.generateEntityObjects(entity, seed);

		// THEN
		assertThat(result).hasSize(10);
	}

	@Test
	public void generateAll_should_return_only_numbers_between_minus2_and_2_over_100_generated_entities() {

		// GIVEN
		long seed = 1l;
		Model model = new ModelBuilder().build();
		int amount = 100;
		Entity entity = model.newEntity("test", amount);
		String propertyName = "number";
		entity.addProperty(propertyName, NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));
		model.addEntity(entity);

		// WHEN
		Map<Entity, List<GeneratedObject>> result = model.generateEntitiesObjectsAll(seed);

		// THEN
		assertThat(result.get(entity)).hasSize(amount);
		assertThat(extractValuesForProperty(propertyName, result.get(entity))).containsOnly(BigDecimal.valueOf(-2),
				BigDecimal.valueOf(-1), BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2));
	}

	@Test
	public void iterator_should_return_a_new_iterator() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		entity.addProperty("number", NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));
		model.addEntity(entity);
		long seed = 1l;

		// WHEN
		GeneratedObjectIterator result = model.iterator("test", seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void iterator_should_get_same_objects_than_all_entities() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		entity.addProperty("number", NebulaGenerators.random(),
				NebulaTypes.number().range().withMin(BigDecimal.valueOf(-2)).withMax(BigDecimal.valueOf(2)));
		model.addEntity(entity);
		long seed = 1l;
		List<GeneratedObject> allObjects = model.generateEntityObjects(entity, seed);

		// WHEN
		GeneratedObjectIterator result = model.iterator("test", seed);

		// THEN
		assertThat(result).hasSameElementsAs(allObjects);
	}

	@Test
	public void getEntityByName_should_return_entity() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		String entityName = "test";
		Entity entity = model.newEntity(entityName, amount);
		model.addEntity(entity);

		// WHEN
		Entity result = model.getEntityByName(entityName);

		// THEN
		assertThat(result).isEqualTo(entity);
	}

	@Test
	public void getEntityByName_should_throw_exception_when_unexisting_entity_is_passed() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		model.addEntity(entity);

		// WHEN
		catchException(model).getEntityByName("unexisting");

		// THEN
		assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unexisting' not exists in model");
	}

	@Test
	public void generateEntity_should_generate_entity_for_index_0() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;

		// WHEN
		GeneratedObject result = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateEntity_should_generate_10_different_objects() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;

		// WHEN
		List<GeneratedObject> result = model.generateEntityObjects(entity, seed);

		// THEN
		assertThat(result).doesNotHaveDuplicates();
	}

	@Test
	public void generateEntity_should_generate_the_same_entity_for_index_0() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;

		// WHEN
		GeneratedObject object1 = model.generateEntityObject(entity, entityIndex, seed);
		GeneratedObject object2 = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(object1).isEqualTo(object2);
	}

	@Test
	public void generateEntity_should_generate_the_same_entity_for_index_0_comparing_to_generate_all_entities() {

		// GIVEN
		Model model = new ModelBuilder().build();
		int amount = 10;
		Entity entity = model.newEntity("test", amount);
		model.addEntity(entity);
		long seed = 1l;
		long entityIndex = 0l;
		GeneratedObject genratedObjectAtIndex0 = model.generateEntityObjects(entity, seed).get(0);

		// WHEN
		GeneratedObject result = model.generateEntityObject(entity, entityIndex, seed);

		// THEN
		assertThat(result).isEqualTo(genratedObjectAtIndex0);
	}

	private List<Object> extractValuesForProperty(String propertyName, List<GeneratedObject> generatedObjects) {
		List<Object> values = new ArrayList<>();
		for (GeneratedObject generatedObject : generatedObjects) {
            values.addAll(generatedObject.getGeneratedProperties().stream().filter(generatedProperty -> generatedProperty.getPropertyName().equals(propertyName)).map(generatedProperty -> generatedProperty.getPropertyValue().getObject()).collect(Collectors.toList()));
		}
		return values;
	}
}
