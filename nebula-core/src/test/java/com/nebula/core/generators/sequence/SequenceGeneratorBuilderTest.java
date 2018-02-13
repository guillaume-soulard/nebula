package com.nebula.core.generators.sequence;

import com.nebula.core.generators.Generator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SequenceGeneratorBuilderTest {

	@Test
	public void new_SequenceGeneratorBuilder_should_set_allowCycle_to_true_by_default() {

		// GIVEN
		SequenceGeneratorBuilder builder;

		// WHEN
		builder = new SequenceGeneratorBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("allowCycle", true);
	}

	@Test
	public void build_should_return_a_new_instance_of_SequanceGenerator() {

		// GIVEN
		SequenceGeneratorBuilder builder = new SequenceGeneratorBuilder();

		// WHEN
		Generator result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(SequenceGenerator.class);
	}

	@Test
	public void noCycle_should_set_allowCycle_to_false() {

		// GIVEN
		SequenceGeneratorBuilder builder = new SequenceGeneratorBuilder();

		// WHEN
		SequenceGeneratorBuilder result = builder.noCycle();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("allowCycle", false);
	}
}
