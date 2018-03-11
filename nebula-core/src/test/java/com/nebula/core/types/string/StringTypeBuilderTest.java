package com.nebula.core.types.string;

import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class StringTypeBuilderTest {

	@Test
	public void build_should_return_a_new_instance_of_StringType() {

		// GIVEN
		TypeBuilder builder = new StringTypeBuilder();

		// WHEN
		Type result = builder.build(ModelBuilder.newModel().build());

		// THEN
		assertThat(result).isInstanceOf(StringType.class);
	}

	@Test
	public void newStringTypeBuilder_should_set_null_as_pattern() {

		// GIVEN
		StringTypeBuilder builder = null;

		// WHEN
		builder = new StringTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("pattern", null);
	}

	@Test
	public void withPattern_should_set_pattern_in_builder() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		String pattern = "[a-b]{1,10}";

		// WHEN
		StringTypeBuilder result = builder.withPattern(pattern);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("pattern", pattern);
	}

	@Test
	public void withPattern_should_throw_a_NebulaException_when_given_pattern_id_null() {

		// GIVEN
		StringTypeBuilder builder = new StringTypeBuilder();
		String pattern = null;

		// WHEN
		catchException(builder).withPattern(pattern);

		// THEN
		assertThat((Exception) (Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("pattern is null");
	}
}
