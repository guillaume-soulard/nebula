package com.nebula.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewEntityTest {

	@Test
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
	void newEntity_should_create_new_instance_of_entity_with_an_other_entity_as_name() {

		// GIVEN
		Entity entity;
		String entityName = "an other_entity";

		// WHEN
		entity = ModelBuilder.newEmptyModel().build().newEntity(entityName, 1);

		// THEN
		assertThat(entity).isNotNull().hasFieldOrPropertyWithValue("name", entityName);
	}

	@Test
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
