package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.RandomGenerator;
import com.nebula.core.generators.SequanceGenerator;

public class NebulaGeneratorsTest {

	@Test
	public void random_should_return_a_new_instance_of_RandomGenerator() {
		// GIVEN

		// WHEN
		RandomGenerator result = NebulaGenerators.random();

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void sequance_should_return_a_new_instance_of_SequanceGenerator() {
		// GIVEN

		// WHEN
		SequanceGenerator result = NebulaGenerators.sequance();

		// THEN
		assertThat(result).isInstanceOf(SequanceGenerator.class);
	}
}
