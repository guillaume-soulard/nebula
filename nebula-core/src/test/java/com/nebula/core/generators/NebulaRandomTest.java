package com.nebula.core.generators;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NebulaRandomTest {

	@Test
	public void nextIndex_should_return_0_with_integer_range_0_0() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.ZERO).build(new ModelBuilder().build());

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void nextIndex_should_return_0_with_integer_range_10_10() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build(new ModelBuilder().build());

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void nextIndex_should_return_index_between_0_and_10_with_integer_range_minus_1000_and_minus_990() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.valueOf(-1000))
				.withMax(BigDecimal.valueOf(-990)).build(new ModelBuilder().build());

		// WHEN
		List<Long> result = new ArrayList<>();
		for (int i = 1; i <= 10000; i++) {
			result.add(nebulaRandom.nextIndex(range));
		}

		// THEN
		assertThat(result).containsAll(Arrays.asList(0l, 1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l));
	}

	@Test
	public void nextIndex_should_not_throw_exception_when_max_bound_is_integer_max_and_min_bound_is_integer_min() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		long min = 0l;
		long max = 10l;
		when(type.getMinRange()).thenReturn(min);
		when(type.getMaxRange()).thenReturn(max);

		// WHEN
		Long result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isBetween(min, max);
	}

	@Test
	public void randomBetween_should_return_0() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);

		// WHEN
		Long result = nebulaRandom.randomBetween(0l, 0l);

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void randomBetween_should_return_1() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);

		// WHEN
		Long result = nebulaRandom.randomBetween(1l, 1l);

		// THEN
		assertThat(result).isEqualTo(1l);
	}
}
