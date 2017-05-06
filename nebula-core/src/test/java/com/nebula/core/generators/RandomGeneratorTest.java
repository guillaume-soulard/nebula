package com.nebula.core.generators;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.NebulaTypes;
import com.nebula.core.types.Type;

public class RandomGeneratorTest {

	@Test
	public void random_should_return_a_new_instance_of_RandomGenerator() throws NebulaException {

		// GIVEN

		// WHEN
		RandomGenerator generator = new RandomGenerator();

		// THEN
		assertThat(generator).isNotNull();
	}

	@Test
	public void generate_should_return_one() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		RandomGenerator generator = new RandomGenerator();
		generator.init(nebulaRandom);
		Type type = NebulaTypes.number().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE).build();

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.ONE);
	}

	@Test
	public void generate_should_return_ten() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		RandomGenerator generator = new RandomGenerator();
		generator.init(nebulaRandom);
		Type type = NebulaTypes.number().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build();

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.TEN);
	}

	@Test
	public void generate_should_return_a_number_between_0_and_10() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		RandomGenerator generator = new RandomGenerator();
		generator.init(nebulaRandom);
		Type type = NebulaTypes.number().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build();

		// WHEN
		List<GeneratedObject> result = new ArrayList<GeneratedObject>();

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
	public void init_should_throw_exception_when_nebulaRandom_is_null() throws NebulaException {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();

		// WHEN
		catchException(generator).init(null);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("nebulaRandom is null");
	}

	@Test
	public void generate_should_throw_exception_when_nebula_random_is_null() throws NebulaException {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		Type type = NebulaTypes.number().build();

		// WHEN
		catchException(generator).generate(type);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("nebulaRandom is null");
	}
}
