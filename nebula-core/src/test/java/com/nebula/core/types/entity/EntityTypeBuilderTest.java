package com.nebula.core.types.entity;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class EntityTypeBuilderTest {

	@Test
    @DisplayName("build should return a new instance of EntityType")
	void build_should_return_a_new_instance_of_EntityType() {

		// GIVEN
		EntityTypeBuilder builder = new EntityTypeBuilder();

		// WHEN
		Type result = builder.withName("test").build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(EntityType.class);
	}

	@Test
    @DisplayName("ofType should set entity in builder s field")
	void ofType_should_set_entity_in_builder_s_field() {

		// GIVEN
		EntityTypeBuilder builder = new EntityTypeBuilder();
		String entityName = "test";

		// WHEN
		EntityTypeBuilder result = builder.withName(entityName);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("entityName", entityName);
	}

	@Test
    @DisplayName("ofType should throw exception when entityName is null")
	void ofType_should_throw_exception_when_entityName_is_null() {

		// GIVEN
		EntityTypeBuilder builder = new EntityTypeBuilder();

		// WHEN
		catchException(builder).withName(null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("entityName is null");
	}
}
