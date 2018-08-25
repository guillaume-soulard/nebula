package com.nebula.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewEntityTest {

	@Test
    @DisplayName("newEntity should create new instance of entity with test as name")
	void newEntity_should_create_new_instance_of_entity_with_test_as_name() {

		// GIVEN
		Entity entity;
		String entityName = "test";

		// WHEN
		entity = ModelBuilder.newEmptyModel().build().newEntity(entityName, 1);

		// THEN
		assertThat(entity).isNotNull().hasFieldOrPropertyWithValue("name", entityName)
				.hasFieldOrPropertyWithValue("amount", 1L);
	}

	@Test
    @DisplayName("newEntity should create new instance of entity with an other entity as name")
	void newEntity_should_create_new_instance_of_entity_with_an_other_entity_as_name() {

		// GIVEN
		Entity entity;
        String entityName = "an other entity";

		// WHEN
		entity = ModelBuilder.newEmptyModel().build().newEntity(entityName, 1);

		// THEN
		assertThat(entity).isNotNull().hasFieldOrPropertyWithValue("name", entityName);
	}

	@Test
    @DisplayName("newEntity should throw NebulaException when null name is passed")
	void newEntity_should_throw_NebulaException_when_null_name_is_passed() {

		// GIVEN
		Throwable exception = null;

		// WHEN
		try {
			ModelBuilder.newEmptyModel().build().newEntity(null, 1);
		} catch (Throwable e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entity name is null");
	}
}
