package com.nebula.core.types.entity;

import com.nebula.core.*;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EntityTypeTest {

	private static final String ENTITY_NAME = "test";

	@Test
    @DisplayName("new EntityType should throw exception when entity is null")
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
    @DisplayName("generateObject should return a non null object")
	void generateObject_should_return_a_non_null_object() {

		// GIVEN
		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1L);

		// WHEN
        GeneratedObject result = type.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("generateObject should return a GeneratedObject with a list of properties")
	void generateObject_should_return_a_GeneratedObject_with_a_list_of_properties() {

		EntityType type = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(type, 1L);

		// WHEN
        GeneratedObject result = type.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("object", null).hasNoNullFieldsOrPropertiesExcept("object");
	}

	@Test
    @DisplayName("init should set context")
	void init_should_set_context() {

		EntityType type = new EntityType(ENTITY_NAME);
		GenerationContext context = initEntityTypeWithEntityAmount(type, 1L);

		// THEN
		assertThat(type).hasFieldOrPropertyWithValue("context", context);
	}

	@Test
    @DisplayName("getMinRange should return 0l")
	void getMinRange_should_return_0l() {

		EntityType entityType = new EntityType(ENTITY_NAME);

		// WHEN
		Long result = entityType.getMinRange();

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("getMaxRange should return 0l")
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
    @DisplayName("getMaxRange should return 1l")
	void getMaxRange_should_return_1l() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2L);

		// WHEN
		Long result = entityType.getMaxRange();

		// THEN
		assertThat(result).isEqualTo(1L);
	}

	@Test
    @DisplayName("generateObject should generate same entity for same index")
	void generateObject_should_generate_same_entity_for_same_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		long index = 0L;
		initEntityTypeWithEntityAmount(entityType, 1L);
        GeneratedObject firstResult = entityType.generateObject(new GeneratedProperties(Collections.emptyList()), index);

		// WHEN
        GeneratedObject result = entityType.generateObject(new GeneratedProperties(Collections.emptyList()), index);

		// THEN
		assertThat(result).isEqualTo(firstResult);
	}

	@Test
    @DisplayName("generateObject should generate different entities for two different indexes")
	void generateObject_should_generate_different_entities_for_two_different_indexes() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 2L);
        GeneratedObject firstResult = entityType.generateObject(new GeneratedProperties(Collections.emptyList()), 0L);

		// WHEN
        GeneratedObject result = entityType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L);

		// THEN
		assertThat(result).isNotEqualTo(firstResult);
	}

	@Test
    @DisplayName("generateObject should throw exception when index is negative")
	void generateObject_should_throw_exception_when_index_is_negative() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN

		// THEN
        assertThatThrownBy(() -> entityType.generateObject(new GeneratedProperties(Collections.emptyList()), -1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("generateObject should throw exception when index is greater than the maximum index")
	void generateObject_should_throw_exception_when_index_is_greater_than_the_maximum_index() {

		EntityType entityType = new EntityType(ENTITY_NAME);
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN

		// THEN
        assertThatThrownBy(() -> entityType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("requested object is out of range");
	}

	@Test
    @DisplayName("generateObject should throw exception when entity name is unknown")
	void generateObject_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN

		// THEN
        assertThatThrownBy(() -> entityType.generateObject(new GeneratedProperties(Collections.emptyList()), 1L))
                .isInstanceOf(NebulaException.class)
				.hasMessage("entity 'unknown entity' is not defined in model");
	}

	@Test
    @DisplayName("getMaxRange should throw exception when entity name is unknown")
	void getMaxRange_should_throw_exception_when_entity_name_is_unknown() {

		EntityType entityType = new EntityType("unknown entity");
		initEntityTypeWithEntityAmount(entityType, 1L);

		// WHEN

		// THEN
        assertThatThrownBy(entityType::getMaxRange)
                .isInstanceOf(NebulaException.class)
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
