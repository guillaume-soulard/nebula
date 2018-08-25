package com.nebula.core.types.list;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.TypeBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListTypeBuilderTest {

	@Test
    @DisplayName("of should return ListTypeOfTypeBuilder with generator and type builder sets")
	void of_should_return_ListTypeOfTypeBuilder_with_generator_and_type_builder_sets() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		TypeBuilder type = NebulaTypes.bool();
		GeneratorBuilder generator = NebulaGenerators.random();

		// WHEN
		ListTypeOfTypeBuilder result = builder.of(generator, type);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("generator", generator).hasFieldOrPropertyWithValue("type",
				type);
	}

	@Test
    @DisplayName("of should return ListTypeAmongItems Builder with generator sets")
	void of_should_return_ListTypeAmongItems_Builder_with_generator_sets() {

		// GIVEN
		ListTypeBuilder builder = new ListTypeBuilder();
		GeneratorBuilder random = NebulaGenerators.random();

		// WHEN
		ListTypeAmongItemsBuilder result = builder.of(random);

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("generator", random);
	}
}
