package com.nebula.core;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.nebula.core.types.Type;

public class ModelTest {

	@Test
	public void add_should_add_given_type_in_model() throws NebulaException {
		// GIVEN
		Entity entity = Nebula.newEntity("test", 1);
		Model model = Nebula.newModel();

		// WHEN
		model.addEntity(entity);

		// THEN
		assertThat(model.getEntities()).containsOnly(entity);
	}

	@Test
	public void add_should_throw_nubula_exception_when_null_type_is_passed() throws NebulaException {
		// GIVEN
		Entity entity = null;
		Model model = Nebula.newModel();

		// WHEN
		catchException(model).addEntity(entity);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("type is null");
	}

	@Test
	public void generate_should_generate_10_objects() throws NebulaException {

		// GIVEN
		long seed = 1l;
		Model model = Nebula.newModel();
		Entity entity = Nebula.newEntity("test", 10);
		entity.addProperty("number", NebulaTypes.number(), NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Type, List<GeneratedObject>> result = model.generate(seed);

		// THEN
		assertThat(result).containsOnlyKeys(entity);
		assertThat(result.get(entity)).hasSize(10);
	}

	@Test
	public void generate_should_return_only_numbers_between_minus2_and_2_over_100000_generated_entities()
			throws NebulaException {

		// GIVEN
		long seed = 1l;
		Model model = Nebula.newModel();
		Entity entity = Nebula.newEntity("test", 100000);
		entity.addProperty("number", NebulaTypes.number().withMin(-2).withMax(2), NebulaGenerators.random());
		model.addEntity(entity);

		// WHEN
		Map<Type, List<GeneratedObject>> result = model.generate(seed);

		// THEN
		assertThat(result.get(entity)).hasSize(100000);
		assertThat(extractPropertiesValues(result.get(entity))).containsOnly(-2l, -1l, 0l, 1l, 2l);
	}

	private List<Object> extractPropertiesValues(List<GeneratedObject> generatedObjects) {
		List<Object> objects = new ArrayList<>();
		for (GeneratedObject generatedObject : generatedObjects) {
			for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
				objects.add(generatedProperty.getPropertyValue().getObject());
			}
		}
		return objects;
	}
}
