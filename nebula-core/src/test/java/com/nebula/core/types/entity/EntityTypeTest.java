package com.nebula.core.types.entity;

import com.nebula.core.*;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EntityTypeTest {

	private static final String ENTITY_NAME = "test";

	@Test
	void new_EntityType_should_throw_exception_when_entity_is_null() {

		// GIVEN
		Exception exception = null;

		// WHEN
		try {
			new EntityType(null);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entityName is null");
	}

	@Test
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1L);

		// WHEN
        GeneratedObject result = type.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void generateObject_should_return_a_GeneratedObject_with_a_list_of_properties() {

		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1L);

		// WHEN
        GeneratedObject result = type.generateObject(Collections.emptyList(), 0L);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("object", null).hasNoNullFieldsOrPropertiesExcept("object");
	}

	@Test
	void init_should_set_context() {

		EntityType type = new EntityType(ENTITY_NAME);
		GenerationContext context = initEntityTypeWithEntityAmount(type, 1L);

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("context", context);
	}

	@Test
	void getMinRange_should_return_0l() {

		EntityType entityType = new EntityType(ENTITY_NAME);

		// WHEN
		Long result = entityType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_0l() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		NebulaRandom nebulaRandom = new NebulaRandom(1L);
		Model model = mock(Model.class);
		when(model.getEntityByName(ENTITY_NAME)).thenReturn(entityWithAmount(1L));
		long entityIndex = 0L;
		entityType.init(new GenerationContext(nebulaRandom, model, entityIndex, 1, 10));

		// WHEN
		Long result = entityType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
	void getMaxRange_should_return_1l() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2L);

		// WHEN
		Long result = entityType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
	void generateObject_should_generate_same_entity_for_same_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		long index = 0L;
		initEntityTypeWithEntityAmount(entityType, 1L);
        GeneratedObject firstResult = entityType.generateObject(Collections.emptyList(), index);

		// WHEN
        GeneratedObject result = entityType.generateObject(Collections.emptyList(), index);

		// THEN
		assertThat(result).isEqualTo(firstResult);
	}

	@Test
	void generateObject_should_generate_different_entities_for_two_different_indexes() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2L);
        GeneratedObject firstResult = entityType.generateObject(Collections.emptyList(), 0L);

		// WHEN
        GeneratedObject result = entityType.generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat(result).isNotEqualTo(firstResult);
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_negative() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN
        catchException(entityType).generateObject(Collections.emptyList(), -1L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	void generateObject_should_throw_exception_when_index_is_greater_than_the_maximum_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN
        catchException(entityType).generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
	void generateObject_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN
        catchException(entityType).generateObject(Collections.emptyList(), 1L);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unknown entity' is not defined in model");
	}

	@Test
	void getMaxRange_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN
		catchException(entityType).getMaxRange();

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unknown entity' is not defined in model");
	}

	private Entity entityWithAmount(long amount) {
		return new Entity(ModelBuilder.newEmptyModel().build(), ENTITY_NAME, amount, new PropertyBuilder());
	}

	private GenerationContext initEntityTypeWithEntityAmount(EntityType type, long entityAmount) {
		Model model = mock(Model.class);
		when(model.getEntityByName(ENTITY_NAME)).thenReturn(entityWithAmount(entityAmount));
		NebulaRandom nebulaRandom = new NebulaRandom(1L);
		long entityIndex = 0L;
		GenerationContext context = new GenerationContext(nebulaRandom, model, entityIndex, 1, 10);
		type.init(context);
		return context;
	}
}
