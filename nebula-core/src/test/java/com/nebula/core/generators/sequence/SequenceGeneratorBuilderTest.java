package com.nebula.core.generators.sequence;

import com.nebula.core.generators.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceGeneratorBuilderTest {

	@Test
    @DisplayName("new SequenceGeneratorBuilder should set allowCycle to true by default")
	void new_SequenceGeneratorBuilder_should_set_allowCycle_to_true_by_default() {

		// GIVEN
		SequenceGeneratorBuilder builder;

		// WHEN
		builder = new SequenceGeneratorBuilder();

		// THEN
		assertThat(builder).hasFieldOrPropertyWithValue("allowCycle", true);
	}

	@Test
    @DisplayName("build should return a new instance of SequanceGenerator")
	void build_should_return_a_new_instance_of_SequanceGenerator() {

		// GIVEN
		SequenceGeneratorBuilder builder = new SequenceGeneratorBuilder();

		// WHEN
		Generator result = builder.build();

		// THEN
		assertThat(result).isInstanceOf(SequenceGenerator.class);
	}

	@Test
    @DisplayName("noCycle should set allowCycle to false")
	void noCycle_should_set_allowCycle_to_false() {

		// GIVEN
		SequenceGeneratorBuilder builder = new SequenceGeneratorBuilder();

		// WHEN
		SequenceGeneratorBuilder result = builder.noCycle();

		// THEN
		assertThat(result).hasFieldOrPropertyWithValue("allowCycle", false);
	}
}
