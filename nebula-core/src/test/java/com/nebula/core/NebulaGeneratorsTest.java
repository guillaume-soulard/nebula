package com.nebula.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.RandomGenerator;

public class NebulaGeneratorsTest {

	@Test
	public void random_should_return_a_new_instance_of_RandomGenerator() throws NebulaException {
		// GIVEN
		Generator generator;

		// WHEN
		generator = NebulaGenerators.random();

		// THEN
		assertThat(generator).isInstanceOf(RandomGenerator.class);
	}
}
