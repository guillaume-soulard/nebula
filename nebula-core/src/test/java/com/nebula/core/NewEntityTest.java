package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.nebula.Nebula;
import org.junit.Test;

public class NewEntityTest {

	@Test
	public void newEntity_should_create_new_instance_of_entity_with_test_as_name() {

		// GIVEN
		Entity entity = null;
		String entityName = "test";

		// WHEN
		entity = Nebula.newEntity(entityName, 1);

		// THEN
		assertThat(entity).isNotNull().hasFieldOrPropertyWithValue("name", entityName)
				.hasFieldOrPropertyWithValue("amount", 1l);
	}

	@Test
	public void newEntity_should_create_new_instance_of_entity_with_an_other_entity_as_name() {

		// GIVEN
		Entity entity = null;
		String entityName = "an other_entity";

		// WHEN
		entity = Nebula.newEntity(entityName, 1);

		// THEN
		assertThat(entity).isNotNull().hasFieldOrPropertyWithValue("name", entityName);
	}

	@Test
	public void newEntity_should_throw_NebulaException_when_null_name_is_passed() {

		// GIVEN
		String entityName = null;
		Throwable exception = null;

		// WHEN
		try {
			Nebula.newEntity(entityName, 1);
		} catch (Throwable e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entity name is null");
	}
}
