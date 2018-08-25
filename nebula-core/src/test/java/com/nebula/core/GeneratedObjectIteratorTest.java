package com.nebula.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GeneratedObjectIteratorTest {

	@Test
    @DisplayName("new GeneratedObjectIterator should set model and entity and seed in fields")
	void new_GeneratedObjectIterator_should_set_model_and_entity_and_seed_in_fields() {

		// GIVEN
		GeneratedObjectIterator iterator;
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = mock(Entity.class);
		long seed = 1L;

		// WHEN
		iterator = new GeneratedObjectIterator(model, entity, seed);

		// THEN
		assertThat((Object) iterator).hasFieldOrPropertyWithValue("model", model)
				.hasFieldOrPropertyWithValue("entity", entity).hasFieldOrPropertyWithValue("seed", seed);
	}

	@Test
    @DisplayName("hasNext should return true when entity amount is 1")
	void hasNext_should_return_true_when_entity_amount_is_1() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = new Entity(model, "test", 1L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
    @DisplayName("hasNext should return true when entity amount is strictly positive")
	void hasNext_should_return_true_when_entity_amount_is_strictly_positive() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = new Entity(model, "test", 67L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
    @DisplayName("hasNext should return false when entity amount is 0")
	void hasNext_should_return_false_when_entity_amount_is_0() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = new Entity(model, "test", 0L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("hasNext should return true when next calls not exceed total amount of entity")
	void hasNext_should_return_true_when_next_calls_not_exceed_total_amount_of_entity() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = new Entity(model, "test", 2L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
    @DisplayName("hasNext should return false when next calls equal total amount of entity")
	void hasNext_should_return_false_when_next_calls_equal_total_amount_of_entity() {

		// GIVEN
		Model model = ModelBuilder.newEmptyModel().build();
		Entity entity = new Entity(model ,"test", 2L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();
		iterator.next();

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isFalse();
	}

	@Test
    @DisplayName("next should return the first entity")
	void next_should_return_the_first_entity() {

		// GIVEN
		GeneratedObject expectedGeneratedObject = new GeneratedObject(null);
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(model, entityName, 2L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		long index = 0L;
		when(model.generateEntityObject(eq(entity), eq(index), eq(seed))).thenReturn(expectedGeneratedObject);

		// WHEN
		GeneratedObject result = iterator.next();

		// THEN
		assertThat(result).isEqualTo(expectedGeneratedObject);
		verify(model, times(1)).generateEntityObject(eq(entity), eq(index), eq(seed));
	}

	@Test
    @DisplayName("next should throw NoSuchElementException when iterator has no more generated objects")
	void next_should_throw_NoSuchElementException_when_iterator_has_no_more_generated_objects() {

		// GIVEN
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(model, entityName, 1L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();

		// WHEN
		catchException(iterator).next();

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NoSuchElementException.class)
				.hasMessage("Iteration has no more elements");
	}

	@SuppressWarnings("unchecked")
	@Test
    @DisplayName("next should throw NebulaException when model throw exception")
	void next_should_throw_NebulaException_when_model_throw_exception() {

		// GIVEN
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(model, entityName, 2L, new PropertyBuilder());
		long seed = 1L;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		when(model.generateEntityObject(any(Entity.class), anyLong(), anyLong())).thenThrow(NebulaException.class);

		// WHEN
		catchException(iterator).next();

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class);
	}
}
