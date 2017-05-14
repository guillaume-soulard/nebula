package com.nebula.core.types.list;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ListTypeBuilderTest {

	@Test
	public void build_should_return_a_non_null_type() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();

		// WHEN
		Type result = builder.of(NebulaGenerators.random().build(), NebulaGenerationTypes.bool()).build();

		// THEN
		assertThat(result).isInstanceOf(ListType.class);
	}

	@Test
	public void ofType_should_set_list_type() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		TypeBuilder type = NebulaGenerationTypes.bool();
		Generator generator = NebulaGenerators.random().build();

		// WHEN
		ListTypeBuilder result = builder.of(generator, type);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("generator", generator)
				.hasFieldOrPropertyWithValue("typeBuilder", type);
	}

	@Test
	public void ofType_should_throw_exception_when_generator_is_null() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		TypeBuilder type = NebulaGenerationTypes.bool();
		Generator generator = null;

		// WHEN
		catchException(builder).of(generator, type);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("generator is null");
	}

	@Test
	public void ofType_should_throw_exception_when_typeBuilder_is_null() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		TypeBuilder type = null;
		Generator generator = NebulaGenerators.random().build();

		// WHEN
		catchException(builder).of(generator, type);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("typeBuilder is null");
	}

	@Test
	public void withMinSize_should_set_size_in_builder() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		int value = 1;

		// WHEN
		ListTypeBuilder result = builder.withMinSize(value);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minSize", value);
	}

	@Test
	public void withMinSize_should_throw_exception_when_value_is_negative() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		int value = -1;

		// WHEN
		catchException(builder).withMinSize(value);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("minSize is negative");
	}

	@Test
	public void withMaxSize_should_set_size_in_builder() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		int value = 1;

		// WHEN
		ListTypeBuilder result = builder.withMaxSize(value);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", value);
	}

	@Test
	public void new_ListTypeBuilder_should_set_defaults() {

		// GIVEN
		ListTypeBuilder builder = null;

		// WHEN
		builder = new ListTypeBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("minSize", 0).hasFieldOrPropertyWithValue("maxSize", 10)
				.hasFieldOrPropertyWithValue("typeBuilder", null).hasFieldOrPropertyWithValue("generator", null);
	}

	@Test
	public void build_should_throw_exception_when_min_is_greater_than_max() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		builder.of(NebulaGenerators.random().build(), NebulaGenerationTypes.bool()).withMaxSize(1).withMinSize(10);

		// WHEN
		catchException(builder).build();

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("maxSize must be greater than minSize");
	}
}
