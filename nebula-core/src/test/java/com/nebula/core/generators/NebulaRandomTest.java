package com.nebula.core.generators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
		Type range = NebulaTypes.number().withMin(BigDecimal.ZERO).withMax(BigDecimal.ZERO).build();

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void nextIndex_should_return_0_with_integer_range_10_10() throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.number().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build();

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0l);
	}

	@Test
	public void nextIndex_should_return_index_between_0_and_10_with_integer_range_minus_1000_and_minus_990()
			throws NebulaException {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0l);
		Type range = NebulaTypes.number().withMin(BigDecimal.valueOf(-1000)).withMax(BigDecimal.valueOf(-990)).build();

		// WHEN
		List<Long> result = new ArrayList<Long>();
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
		when(type.getMinRange()).thenReturn(Long.MIN_VALUE);
		when(type.getMaxRange()).thenReturn(Long.MAX_VALUE);

		// WHEN
		Long result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isBetween(Long.MIN_VALUE, Long.MAX_VALUE);
	}

	@Test
	public void nextIndex_should_return_0_when_range_is_integer_max_as_min_and_integer_max_as_max() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		when(type.getMinRange()).thenReturn(Long.MAX_VALUE);
		when(type.getMaxRange()).thenReturn(Long.MAX_VALUE);

		// WHEN
		Long result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void nextIndex_should_return_0_when_range_is_integer_min_as_min_and_integer_min_as_max() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1l);
		Type type = mock(Type.class);
		when(type.getMinRange()).thenReturn(Long.MIN_VALUE);
		when(type.getMaxRange()).thenReturn(Long.MIN_VALUE);

		// WHEN
		Long result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isEqualTo(0);
	}
}
