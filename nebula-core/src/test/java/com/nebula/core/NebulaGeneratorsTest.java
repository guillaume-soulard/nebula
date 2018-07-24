package com.nebula.core;

import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.sequence.SequenceGeneratorBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NebulaGeneratorsTest {

	@Test
	void random_should_return_a_new_instance_of_RandomGenerator() {
		// GIVEN

		// WHEN
		RandomGeneratorBuilder result = NebulaGenerators.random();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	void sequance_should_return_a_new_instance_of_SequanceGenerator() {
		// GIVEN

		// WHEN
		SequenceGeneratorBuilder result = NebulaGenerators.sequence();

		// THEN
		assertThat(result).isInstanceOf(SequenceGeneratorBuilder.class);
	}
}
