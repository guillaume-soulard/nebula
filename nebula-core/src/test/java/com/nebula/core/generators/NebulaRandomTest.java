package com.nebula.core.generators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nebula.core.NebulaException;
import com.nebula.core.NebulaTypes;
import com.nebula.core.types.Type;

public class NebulaRandomTest {

	@Test
	public void nextIndex_should_return_0_with_integer_range_0_0() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.integer().withMin(0).withMax(0).build();

		// WHEN
		Integer result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void nextIndex_should_return_0_with_integer_range_10_10() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.integer().withMin(10).withMax(10).build();

		// WHEN
		Integer result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void nextIndex_should_return_index_between_0_and_10_with_integer_range_minus_1000_and_minus_990()
			throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.integer().withMin(-1000).withMax(-990).build();

		// WHEN
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i <= 10000; i++) {
			result.add(nebulaRandom.nextIndex(range));
		}

		// THEN
		assertThat(result).containsAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
	}

	@Test
	public void nextIndex_should_not_throw_exception_when_max_bound_is_integer_max_and_min_bound_is_integer_min() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		when(type.getMinRange()).thenReturn(Integer.MIN_VALUE);
		when(type.getMaxRange()).thenReturn(Integer.MAX_VALUE);

		// WHEN
		Integer result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@Test
	public void nextIndex_should_return_0_when_range_is_integer_max_as_min_and_integer_max_as_max() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		when(type.getMinRange()).thenReturn(Integer.MAX_VALUE);
		when(type.getMaxRange()).thenReturn(Integer.MAX_VALUE);

		// WHEN
		Integer result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void nextIndex_should_return_0_when_range_is_integer_min_as_min_and_integer_min_as_max() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		when(type.getMinRange()).thenReturn(Integer.MIN_VALUE);
		when(type.getMaxRange()).thenReturn(Integer.MIN_VALUE);

		// WHEN
		Integer result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isEqualTo(0);
	}
}
