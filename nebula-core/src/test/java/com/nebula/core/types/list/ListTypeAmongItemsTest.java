package com.nebula.core.types.list;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import com.nebula.core.types.constant.ConstantTypeBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListTypeAmongItemsTest {

	private static final BigDecimal BIG_DECIMAL_TWO = BigDecimal.ONE.add(BigDecimal.ONE);

	@Test
    @DisplayName("new ListType should set passed values in fields")
	void new_ListType_should_set_passed_values_in_fields() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ListTypeAmongItems listType;
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };

		// WHEN
		listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("minSize", minSize)
				.hasFieldOrPropertyWithValue("maxSize", maxSize).hasFieldOrPropertyWithValue("generator", generator)
				.hasFieldOrPropertyWithValue("items", new GeneratedObject[] { new GeneratedObject("test") });
	}

	@Test
    @DisplayName("getMinRange should return 0")
	void getMinRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);

		// WHEN
		Long result = listType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 0")
	void getMaxRange_should_return_0() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 1")
	void getMaxRange_should_return_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant(), newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
    @DisplayName("getMaxRange should return 4")
	void getMaxRange_should_return_4() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant(), newConstant(), newConstant(),
				newConstant(), newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);

		// WHEN
		Long result = listType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(4L);
	}

	@Test
    @DisplayName("init should set nebulaRandom")
	void init_should_set_nebulaRandom() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] {};
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		GenerationContext context = mock(GenerationContext.class);

		// WHEN
		listType.init(context);

		// THEN
		assertThat(listType).hasFieldOrPropertyWithValue("context", context);
	}

	@Test
    @DisplayName("generateObject should return a non null object")
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return an ArrayList")
	void generateObject_should_return_an_ArrayList() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).isInstanceOf(ArrayList.class);
	}

	@Test
    @DisplayName("generateObject should return an empty list")
	void generateObject_should_return_an_empty_list() {

		// GIVEN
		int minSize = 0;
		int maxSize = 0;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().isEmpty();
	}

	@Test
    @DisplayName("generateObject should return a list with size 1")
	void generateObject_should_return_a_list_with_size_1() {

		// GIVEN
		int minSize = 1;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().hasSize(1);
	}

	@SuppressWarnings("unchecked")
	@Test
    @DisplayName("generateObject should return a list with different size at index 0 and 1")
	void generateObject_should_return_a_list_with_different_size_at_index_0_and_1() {

		// GIVEN
		int minSize = 0;
		int maxSize = 10;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);
        GeneratedObject resultAtindex0 = listType.generateObject(Collections.emptyList(), 0L);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result.getObject()).asList().size().isNotEqualTo(((List<Object>) resultAtindex0.getObject()).size());
	}

	@Test
    @DisplayName("generateObject should return a the same list size with same index")
	void generateObject_should_return_a_the_same_list_size_with_same_index() {

		// GIVEN
		int minSize = 0;
		int maxSize = 100;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { newConstant() };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);
        GeneratedObject resultAtindex0 = listType.generateObject(Collections.emptyList(), 0L);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().isEqualTo(resultAtindex0.getObject());
	}

	@Test
    @DisplayName("generateObject should return a list contening only items in gien items list")
	void generateObject_should_return_a_list_contening_only_items_in_gien_items_list() {

		// GIVEN
		int minSize = 1;
		int maxSize = 10;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder item = newConstant();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { item };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().hasSize(1).containsOnly(new GeneratedObject("test"));
	}

	@Test
    @DisplayName("generateObject should return an empty list with minSize set to 1")
	void generateObject_should_return_an_empty_list_with_minSize_set_to_1() {

		// GIVEN
		int minSize = 1;
		int maxSize = 1;
		Generator generator = NebulaGenerators.random().build();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] {};
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().isEmpty();
	}

	@Test
    @DisplayName("generateObject should return list that contains items gives by the generator")
	void generateObject_should_return_list_that_contains_items_gives_by_the_generator() {

		// GIVEN
		int minSize = 6;
		int maxSize = 6;
		Generator generator = mock(Generator.class);
		ConstantTypeBuilder item1 = newConstant();
		ConstantTypeBuilder item2 = newConstant();
		ConstantTypeBuilder item3 = newConstant();
		ConstantTypeBuilder[] items = new ConstantTypeBuilder[] { item1, item2, item3 };
		ListTypeAmongItems listType = new ListTypeAmongItems(ModelBuilder.newEmptyModel().build(), minSize, maxSize, generator, items);
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Model model = ModelBuilder.newEmptyModel().build();
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		listType.init(context);
		GeneratedObject index0 = new GeneratedObject(BigDecimal.ZERO);
		GeneratedObject index1 = new GeneratedObject(BigDecimal.ONE);
		GeneratedObject index2 = new GeneratedObject(BIG_DECIMAL_TWO);
        when(generator.generate(eq(Collections.emptyList()), any(Type.class))).thenReturn(index0, index1, index2, index2, index1, index0);

		// WHEN
        GeneratedObject result = listType.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result.getObject()).asList().containsExactly(new GeneratedObject("test"),
				new GeneratedObject("test"),
				new GeneratedObject("test"),
				new GeneratedObject("test"),
				new GeneratedObject("test"),
				new GeneratedObject("test"));
	}

	private ConstantTypeBuilder newConstant() {
		return NebulaTypes.constant("test");
	}
}
