package com.nebula.core.generators.random;

import com.nebula.core.generators.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomGeneratorBuilderTest {

	@Test
    @DisplayName("build should return a non null object")
	void build_should_return_a_non_null_object() {

		// GIVEN
		RandomGeneratorBuilder builder = new RandomGeneratorBuilder();

		// WHEN
		Generator result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(RandomGenerator.class);
	}
}
