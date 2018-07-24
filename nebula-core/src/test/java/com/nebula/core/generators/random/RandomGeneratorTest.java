package com.nebula.core.generators.random;

import com.nebula.core.GeneratedObject;
import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RandomGeneratorTest {

	@Test
	void random_should_return_a_new_instance_of_RandomGenerator() {

		// GIVEN

		// WHEN
		RandomGenerator generator = new RandomGenerator();

		// THEN
		assertThat(generator).isNotNull();
	}

	@Test
	void generate_should_return_one() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(mock(NebulaRandom.class), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE).build(ModelBuilder.newModel().build());

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.ONE);
	}

	@Test
	void generate_should_return_ten() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(mock(NebulaRandom.class), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build(ModelBuilder.newModel().build());

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.TEN);
	}

	@Test
	void generate_should_return_a_number_between_0_and_10() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(new NebulaRandom(0L), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(ModelBuilder.newModel().build());

		// WHEN
		List<GeneratedObject> result = new ArrayList<>();

		for (int i = 1; i <= 10000; i++) {
			result.add(generator.generate(type));
		}

		// THEN
		assertThat(result).extracting("object")
				.containsAll(Arrays.asList(BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2),
						BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5), BigDecimal.valueOf(6),
						BigDecimal.valueOf(7), BigDecimal.valueOf(8), BigDecimal.valueOf(9), BigDecimal.valueOf(10)));
	}

	@Test
	void init_should_throw_exception_when_nebulaRandom_is_null() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();

		// WHEN
		catchException(generator).init(null);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("generationContext is null");
	}

	@Test
	void generate_should_throw_exception_when_nebula_random_is_null() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		Type type = NebulaTypes.number().range().build(ModelBuilder.newModel().build());

		// WHEN
		catchException(generator).generate(type);

		// THEN
		assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
				.hasMessage("generationContext is null");
	}
}
