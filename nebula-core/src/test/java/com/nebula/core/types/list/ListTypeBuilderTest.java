package com.nebula.core.types.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.TypeBuilder;

public class ListTypeBuilderTest {

	@Test
	public void of_should_return_ListTypeOfTypeBuilder_with_generator_and_type_builder_sets() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		TypeBuilder type = NebulaGenerationTypes.bool();
		GeneratorBuilder generator = NebulaGenerators.random();

		// WHEN
		ListTypeOfTypeBuilder result = builder.of(generator, type);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("generator", generator).hasFieldOrPropertyWithValue("type",
				type);
	}

	@Test
	public void of_should_return_ListTypeAmongItems_Builder_with_generator_sets() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		GeneratorBuilder random = NebulaGenerators.random();

		// WHEN
		ListTypeAmongItemsBuilder result = builder.of(random);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("generator", random);
	}
}
