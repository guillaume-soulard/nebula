package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.sequance.SequenceGeneratorBuilder;

public class NebulaGeneratorsTest {

	@Test
	public void random_should_return_a_new_instance_of_RandomGenerator() {
		// GIVEN

		// WHEN
		RandomGeneratorBuilder result = NebulaGenerators.random();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void sequance_should_return_a_new_instance_of_SequanceGenerator() {
		// GIVEN

		// WHEN
		SequenceGeneratorBuilder result = NebulaGenerators.sequence();

		// THEN
		assertThat(result).isInstanceOf(SequenceGeneratorBuilder.class);
	}
}
