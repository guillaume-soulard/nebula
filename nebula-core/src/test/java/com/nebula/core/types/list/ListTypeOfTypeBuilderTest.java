package com.nebula.core.types.list;

import com.nebula.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class ListTypeOfTypeBuilderTest {

	@Test
	public void new_ListTypeOfTypeBuilder_should_set_generator_and_type() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = null;

		// WHEN
		builder = new ListTypeOfTypeBuilder(generator, type);

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("generator", generator).hasFieldOrPropertyWithValue("type",
				type);
	}

	@Test
	public void new_ListTypeOfTypeBuilder_should_throw_exception_when_generator_is_null() {

		// GIVEN
		GeneratorBuilder generator = null;
		TypeBuilder type = NebulaTypes.bool();
		Exception exception = null;

		// WHEN
		try {
			new ListTypeOfTypeBuilder(generator, type);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("generator is null");
	}

	@Test
	public void new_ListTypeOfTypeBuilder_should_throw_exception_when_type_is_null() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = null;
		Exception exception = null;

		// WHEN
		try {
			new ListTypeOfTypeBuilder(generator, type);
		} catch (Exception e) {
			exception = e;
		}

		// THEN
		assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("type is null");
	}

	@Test
	public void withMinSize_should_set_0_as_min_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);

		// WHEN
		AbstractListTypeBuilder result = builder.withMinSize(0);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minSize", 0);
	}

	@Test
	public void withMinSize_should_set_15_as_min_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);

		// WHEN
		AbstractListTypeBuilder result = builder.withMinSize(15);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("minSize", 15);
	}

	@Test
	public void withMaxSize_should_set_0_as_max_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);

		// WHEN
		AbstractListTypeBuilder result = builder.withMaxSize(0);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 0);
	}

	@Test
	public void withMaxSize_should_set_15_as_max_size() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);

		// WHEN
		AbstractListTypeBuilder result = builder.withMaxSize(15);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 15);
	}

	@Test
	public void build_should_return_a_new_instance_of_ListType_with_minSize_and_maxSize_with_defauls_value() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);

		// WHEN
		Type result = builder.build(new ModelBuilder().build());

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("maxSize", 10).hasFieldOrPropertyWithValue("minSize", 0);
	}

	@Test
	public void build_should_throw_exception_when_min_is_greater_than_max() {

		// GIVEN
		GeneratorBuilder generator = NebulaGenerators.random();
		TypeBuilder type = NebulaTypes.bool();
		ListTypeOfTypeBuilder builder = new ListTypeOfTypeBuilder(generator, type);
		builder.withMinSize(10).withMaxSize(0);

		// WHEN
		catchException(builder).build(new ModelBuilder().build());

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("maxSize must be greater than minSize");
	}
}
