package com.nebula.core.types.list;

import com.nebula.core.GeneratedObject;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListTypeOfTypeTest {

	@Test
	void new_ListType_should_set_passed_values_in_fields() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType;

		// WHEN
		listType = new ListTypeOfType(minSize, maxSize, generator, type);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("minSize", minSize)
				.hasFieldOrPropertyWithValue("maxSize", maxSize).hasFieldOrPropertyWithValue("generator", generator)
				.hasFieldOrPropertyWithValue("type", type);
	}

	@Test
	void getMinRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
	void getMaxRange_should_return_15() {

		// GIVEN
		int minSize = 7;
		int maxSize = 22;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(15L);
	}

	@Test
	void init_should_set_nebulaRandom() {

		// GIVEN
		int minSize = 7;
		int maxSize = 22;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);

		// WHEN
		listType.init(context);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("context", context);
	}

	@Test
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
		GeneratedObject result = listType.generateObject(0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void generateObject_should_return_an_ArrayList() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
		GeneratedObject result = listType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).isInstanceOf(ArrayList.class);
	}

	@Test
	void generateObject_should_return_an_empty_list() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);
		// WHEN
		GeneratedObject result = listType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).asList().isEmpty();
	}

	@Test
	void generateObject_should_return_a_list_with_size_1() {

		// GIVEN
		int minSize = 1;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);
		// WHEN
		GeneratedObject result = listType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).asList().hasSize(1);
	}

	@SuppressWarnings("unchecked")
	@Test
	void generateObject_should_return_a_list_with_different_size_at_index_0_and_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 100;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);
		GeneratedObject resultAtIndex0 = listType.generateObject(0L);

		// WHEN
		GeneratedObject resultAtIndex1 = listType.generateObject(1L);

		// THEN
		assertThat(resultAtIndex1.getObject()).asList().size()
				.isNotEqualTo(((List<Object>) resultAtIndex0.getObject()).size());
	}

	@SuppressWarnings("unchecked")
	@Test
	void generateObject_should_return_a_the_same_list_size_with_same_index() {

		// GIVEN
		int minSize = 0;
		int maxSize = 10;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.bool().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);
		GeneratedObject firstResult = listType.generateObject(0L);

		// WHEN
		GeneratedObject secondResult = listType.generateObject(0L);

		// THEN
		assertThat(secondResult.getObject()).asList()
				.containsExactlyElementsOf(((ArrayList<Object>) firstResult.getObject()));
	}

	@Test
	void generateObject_should_generate_a_list_of_string_with_different_values() {

		// GIVEN
		int minSize = 0;
		int maxSize = 10;
		Generator generator = NebulaGenerators.random().build();
		Type type = NebulaTypes.string().build(ModelBuilder.newEmptyModel().build());
		ListTypeOfType listType = new ListTypeOfType(minSize, maxSize, generator, type);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(new NebulaRandom(1L), null, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
		GeneratedObject result = listType.generateObject(0L);

		// THEN
		assertThat(result.getObject()).asList()
				.doesNotHaveDuplicates();
	}
}
