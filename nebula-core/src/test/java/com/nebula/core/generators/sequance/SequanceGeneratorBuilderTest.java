package com.nebula.core.generators.sequance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nebula.core.generators.Generator;

public class SequanceGeneratorBuilderTest {

	@Test
	public void new_SequanceGeneratorBuilder_should_set_allowCycle_to_false_by_default() {

		// GIVEN
		SequanceGeneratorBuilder builder = null;

		// WHEN
		builder = new SequanceGeneratorBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("allowCycle", false);
	}

	@Test
	public void build_should_return_a_new_instance_of_SequanceGenerator() {

		// GIVEN
		SequanceGeneratorBuilder builder = new SequanceGeneratorBuilder();

		// WHEN
		Generator result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(SequanceGenerator.class);
	}

	@Test
	public void cycle_should_set_allowCycle_to_true() {

		// GIVEN
		SequanceGeneratorBuilder builder = new SequanceGeneratorBuilder();

		// WHEN
		SequanceGeneratorBuilder result = builder.cycle();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("allowCycle", true);
	}
}
