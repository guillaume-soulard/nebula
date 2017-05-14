package com.nebula.core.generators.random;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.Generator;

public class RandomGeneratorBuilderTest {

	@Test
	public void build_should_return_a_non_null_object() {

		// GIVEN
		RandomGeneratorBuilder builder = new RandomGeneratorBuilder();

		// WHEN
		Generator result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(RandomGenerator.class);
	}
}
