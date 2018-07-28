package com.nebula.core.types.string;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.junit.jupiter.api.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class StringTypeBuilderTest {

	@Test
	void build_should_return_a_new_instance_of_StringType() {

		// GIVEN
		TypeBuilder builder = new StringTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newEmptyModel().build());

		// THEN
		assertThat(result).isInstanceOf(StringType.class);
	}

	@Test
	void newStringTypeBuilder_should_set_null_as_pattern() {

		// GIVEN
		StringTypeBuilder builder;

		// WHEN
		builder = new StringTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("pattern", null);
	}

	@Test
	void withPattern_should_set_pattern_in_builder() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		String pattern = "[a-b]{1,10}";

		// WHEN
		StringTypeBuilder result = builder.withPattern(pattern);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("pattern", pattern);
	}

	@Test
	void withPattern_should_throw_a_NebulaException_when_given_pattern_id_null() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();

		// WHEN
		catchException(builder).withPattern(null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("pattern is null");
	}
}
