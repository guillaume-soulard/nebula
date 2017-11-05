package com.nebula.core.types.list;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.nebula.Nebula.newModel;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.constant.ConstantTypeBuilder;

public class ListTypeAmongItemsBuilderTest {

	private static final ConstantTypeBuilder[] EMPTY_ITEMS = new ConstantTypeBuilder[] {};

	@Test
	public void new_ListTypeOfTypeBuilder_should_set_generator() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = null;

		// WHEN
		builder = new ListTypeAmongItemsBuilder(generator);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("generator", generator);
	}

	@Test
	public void new_ListTypeOfTypeBuilder_should_throw_exception_when_generator_is_null() {

		// GIVEN
		GeneratorBuilder generator = null;
		Exception exception = null;

		// WHEN
		try {
			new ListTypeAmongItemsBuilder(generator);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("generator is null");
	}

	@Test
	public void withMinSize_should_set_0_as_min_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		AbstractListTypeBuilder result = builder.withMinSize(0);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minSize", 0);
	}

	@Test
	public void withMinSize_should_set_15_as_min_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		AbstractListTypeBuilder result = builder.withMinSize(15);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minSize", 15);
	}

	@Test
	public void withMaxSize_should_set_0_as_max_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		AbstractListTypeBuilder result = builder.withMaxSize(0);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 0);
	}

	@Test
	public void withMaxSize_should_set_15_as_max_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		AbstractListTypeBuilder result = builder.withMaxSize(15);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 15);
	}

	@Test
	public void build_should_return_a_new_instance_of_ListType_with_items_and_minSize_and_maxSize_with_defauls_value() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		Type result = builder.build(newModel());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 10).hasFieldOrPropertyWithValue("minSize", 0)
				.hasFieldOrPropertyWithValue("items", EMPTY_ITEMS);
	}

	@Test
	public void build_should_throw_exception_when_min_is_greater_than_max() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);
		builder.withMinSize(10).withMaxSize(0);

		// WHEN
		catchException(builder).build(newModel());

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("maxSize must be greater than minSize");
	}

	@Test
	public void amongItems_should_set_one_item() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);
		ConstantTypeBuilder item = NebulaGenerationTypes.constant("test");

		// WHEN
		ListTypeAmongItemsBuilder result = builder.amongItems(item);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", new ConstantTypeBuilder[] { item });
	}

	@Test
	public void amongItems_should_set_two_item() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);
		ConstantTypeBuilder item1 = NebulaGenerationTypes.constant("test");
		ConstantTypeBuilder item2 = NebulaGenerationTypes.constant("test");

		// WHEN
		ListTypeAmongItemsBuilder result = builder.amongItems(item1, item2);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", new ConstantTypeBuilder[] { item1, item2 });
	}

	@Test
	public void amongItems_should_set_one_item_to_null_when_null_is_passed() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		ListTypeAmongItemsBuilder result = builder.amongItems(null);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", new ConstantTypeBuilder[] { null });
	}

	@Test
	public void amongItems_should_set_empty_items() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		ListTypeAmongItemsBuilder builder = new ListTypeAmongItemsBuilder(generator);

		// WHEN
		ListTypeAmongItemsBuilder result = builder.amongItems(EMPTY_ITEMS);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("items", EMPTY_ITEMS);
	}
}
