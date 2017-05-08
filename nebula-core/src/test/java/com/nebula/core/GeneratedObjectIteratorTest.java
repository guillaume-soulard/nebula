package com.nebula.core;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Test;

public class GeneratedObjectIteratorTest {

	@Test
	public void new_GeneratedObjectIterator_should_set_model_and_entity_and_seed_in_fields() {

		// GIVEN
		GeneratedObjectIterator iterator = null;
		Model model = new Model();
		Entity entity = mock(Entity.class);
		long seed = 1l;

		// WHEN
		iterator = new GeneratedObjectIterator(model, entity, seed);

		// THEN
		assertThat((Object) iterator).hasFieldOrPropertyWithValue("model", model)
				.hasFieldOrPropertyWithValue("entity", entity).hasFieldOrPropertyWithValue("seed", seed);
	}

	@Test
	public void hasNext_should_return_true_when_entity_amount_is_1() {

		// GIVEN
		Model model = new Model();
		Entity entity = new Entity("test", 1l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void hasNext_should_return_true_when_entity_amount_is_strictly_positive() {

		// GIVEN
		Model model = new Model();
		Entity entity = new Entity("test", 67l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void hasNext_should_return_false_when_entity_amount_is_0() {

		// GIVEN
		Model model = new Model();
		Entity entity = new Entity("test", 0l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void hasNext_should_return_true_when_next_calls_not_exceed_total_amount_of_entity() {

		// GIVEN
		Model model = new Model();
		Entity entity = new Entity("test", 2l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isTrue();
	}

	@Test
	public void hasNext_should_return_false_when_next_calls_equal_total_amount_of_entity() {

		// GIVEN
		Model model = new Model();
		Entity entity = new Entity("test", 2l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();
		iterator.next();

		// WHEN
		boolean result = iterator.hasNext();

		// THEN
		assertThat(result).isFalse();
	}

	@Test
	public void next_should_return_the_first_entity() {

		// GIVEN
		GeneratedObject expectedGeneratedObject = new GeneratedObject(null);
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(entityName, 2l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		long index = 0l;
		when(model.generateEntity(eq(entity), eq(index), eq(seed))).thenReturn(expectedGeneratedObject);

		// WHEN
		GeneratedObject result = iterator.next();

		// THEN
		assertThat(result).isEqualTo(expectedGeneratedObject);
		verify(model, times(1)).generateEntity(eq(entity), eq(index), eq(seed));
	}

	@Test
	public void next_should_throw_NoSuchElementException_when_iterator_has_no_more_generated_objects() {

		// GIVEN
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(entityName, 1l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		iterator.next();

		// WHEN
		catchException(iterator).next();

		// THEN
		assertThat(caughtException()).isInstanceOf(NoSuchElementException.class)
				.hasMessage("Iteration has no more elements");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void next_should_throw_NebulaException_when_model_throw_exception() {

		// GIVEN
		Model model = mock(Model.class);
		String entityName = "test";
		Entity entity = new Entity(entityName, 2l, new PropertyBuilder());
		long seed = 1l;
		GeneratedObjectIterator iterator = new GeneratedObjectIterator(model, entity, seed);
		when(model.generateEntity(any(Entity.class), anyLong(), anyLong())).thenThrow(NebulaException.class);

		// WHEN
		catchException(iterator).next();

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class);
	}
}
