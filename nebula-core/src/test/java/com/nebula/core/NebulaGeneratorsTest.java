package com.nebula.core;

import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.sequence.SequenceGeneratorBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NebulaGeneratorsTest {

	@Test
    @DisplayName("random should return a new instance of RandomGenerator")
	void random_should_return_a_new_instance_of_RandomGenerator() {
		// GIVEN

		// WHEN
		RandomGeneratorBuilder result = NebulaGenerators.random();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
    @DisplayName("sequance should return a new instance of SequanceGenerator")
	void sequance_should_return_a_new_instance_of_SequanceGenerator() {
		// GIVEN

		// WHEN
		SequenceGeneratorBuilder result = NebulaGenerators.sequence();

		// THEN
		assertThat(result).isInstanceOf(SequenceGeneratorBuilder.class);
	}
}
