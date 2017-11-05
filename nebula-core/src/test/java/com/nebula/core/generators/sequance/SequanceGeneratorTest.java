package com.nebula.core.generators.sequance;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.nebula.Nebula.newModel;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.types.Type;

public class SequanceGeneratorTest {

	@Test
	public void generate_should_return_non_null_object() {

		// GIVEN
		SequenceGenerator generator = new SequenceGenerator(false);
		Type type = NebulaGenerationTypes.bool().build(newModel());

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result).isNotNull();
	}

	@Test
	public void generate_should_return_the_first_element_in_given_type() {

		// GIVEN
		SequenceGenerator generator = new SequenceGenerator(false);
		Type type = NebulaGenerationTypes.number().among().items(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN)
				.build(newModel());

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	public void generate_should_return_the_second_element_in_given_type() {

		// GIVEN
		SequenceGenerator generator = new SequenceGenerator(false);
		Type type = NebulaGenerationTypes.number().among().items(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN)
				.build(newModel());
		generator.generate(type);

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.ONE);
	}

	@Test
	public void generate_should_throw_exception_when_sequance_index_limit_is_reach() {

		// GIVEN
		SequenceGenerator generator = new SequenceGenerator(false);
		Type type = NebulaGenerationTypes.number().among().items(BigDecimal.ZERO).build(newModel());
		generator.generate(type);

		// WHEN
		catchException(generator).generate(type);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("sequence reach the maximum index of type (0). Use cycle() to allow sequence to restart");
	}

	@Test
	public void new_SequanceGenerator_should_set_allowCycle() {

		// GIVEN
		SequenceGenerator generator = null;

		// WHEN
		generator = new SequenceGenerator(true);

		// THEN
		assertThat(generator).hasFieldOrPropertyWithValue("allowCycle", true);
	}

	@Test
	public void generate_should_return_first_item_after_sequance_reach_the_maximum_when_cycle_has_been_specified() {

		// GIVEN
		SequenceGenerator generator = new SequenceGenerator(true);
		Type type = NebulaGenerationTypes.number().among().items(BigDecimal.ZERO, BigDecimal.ONE).build(newModel());
		generator.generate(type);
		generator.generate(type);

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isEqualTo(BigDecimal.ZERO);
	}
}
