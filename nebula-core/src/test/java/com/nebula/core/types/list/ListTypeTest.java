package com.nebula.core.types.list;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class ListTypeTest {

	@Test
	public void new_ListType_should_set_passed_values_in_fields() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = null;

		// WHEN
		listType = new ListType(minSize, maxSize, generator, type);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("minSize", minSize)
				.hasFieldOrPropertyWithValue("maxSize", maxSize).hasFieldOrPropertyWithValue("generator", generator)
				.hasFieldOrPropertyWithValue("type", type);
	}

	@Test
	public void getMinRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void getMaxRange_should_return_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1l);
	}

	@Test
	public void getMaxRange_should_return_15() {

		// GIVEN
		int minSize = 7;
		int maxSize = 22;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(15l);
	}

	@Test
	public void init_should_set_nebulaRandom() {

		// GIVEN
		int minSize = 7;
		int maxSize = 22;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);

		// WHEN
		listType.init(nebulaRandom);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("nebulaRandom", nebulaRandom);
	}

	@Test
	public void generateObject_should_return_a_non_null_object() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = listType.generateObject(0l);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generateObject_should_return_an_ArrayList() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = listType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).isInstanceOf(ArrayList.class);
	}

	@Test
	public void generateObject_should_return_an_empty_list() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = listType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).asList().isEmpty();
	}

	@Test
	public void generateObject_should_return_a_list_with_size_1() {

		// GIVEN
		int minSize = 1;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);

		// WHEN
		GeneratedObject result = listType.generateObject(0l);

		// THEN
		assertThat(result.getObject()).asList().hasSize(1);
	}

	@Test
	public void generateObject_should_return_a_list_with_different_size_at_index_0_and_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 100;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);
		GeneratedObject resultAtIndex0 = listType.generateObject(0l);

		// WHEN
		GeneratedObject resultAtIndex1 = listType.generateObject(1l);

		// THEN
		assertThat(resultAtIndex1.getObject()).asList().size()
				.isNotEqualTo(((List<Object>) resultAtIndex0.getObject()).size());
	}

	@Test
	public void generateObject_should_return_a_the_same_list_size_with_same_index() {

		// GIVEN
		int minSize = 0;
		int maxSize = 10;
		Generator generator = NebulaGenerators.random();
		Type type = NebulaGenerationTypes.bool().build();
		ListType listType = new ListType(minSize, maxSize, generator, type);
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		listType.init(nebulaRandom);
		GeneratedObject firstResult = listType.generateObject(0l);

		// WHEN
		GeneratedObject secondResult = listType.generateObject(0l);

		// THEN
		assertThat(secondResult.getObject()).asList()
				.containsExactlyElementsOf(((ArrayList<Object>) firstResult.getObject()));
	}
}
