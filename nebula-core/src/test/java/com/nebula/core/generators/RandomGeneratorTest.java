package com.nebula.core.generators;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
		Type type = NebulaTypes.integer().withMin(1).withMax(1).build();

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(Integer.class).isEqualTo(1);
	}

	@Test
	public void generate_should_return_ten() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = mock(NebulaRandom.class);
		RandomGenerator generator = new RandomGenerator();
		generator.init(nebulaRandom);
		Type type = NebulaTypes.integer().withMin(10).withMax(10).build();

		// WHEN
		GeneratedObject result = generator.generate(type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(Integer.class).isEqualTo(10);
	}

	@Test
	public void generate_should_return_a_number_between_0_and_10() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		RandomGenerator generator = new RandomGenerator();
		generator.init(nebulaRandom);
		Type type = NebulaTypes.integer().withMin(0).withMax(10).build();

		// WHEN
		List<GeneratedObject> result = new ArrayList<GeneratedObject>();

		for (int i = 1; i <= 10000; i++) {
			result.add(generator.generate(type));
		}

		// THEN
		assertThat(result).extracting("object").containsAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
	}

	@Test
	public void init_should_throw_exception_when_nebulaRandom_is_null() throws NebulaException {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		Exception ex = null;

		// WHEN
		catchException(generator).init(null);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("nebulaRandom is null");
	}

	@Test
	public void generate_should_throw_exception_when_nebula_random_is_null() throws NebulaException {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		Type type = NebulaTypes.integer().build();

		// WHEN
		catchException(generator).generate(type);

		// THEN
		assertThat(caughtException()).isInstanceOf(NebulaException.class).hasMessage("nebulaRandom is null");
	}
}
