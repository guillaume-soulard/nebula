package com.nebula.core.generators;

import com.nebula.core.ModelBuilder;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NebulaRandomTest {

	@Test
    @DisplayName("nextIndex should return 0 with integer range 0 0")
	void nextIndex_should_return_0_with_integer_range_0_0() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.ZERO).build(ModelBuilder.newEmptyModel().build());

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("nextIndex should return 0 with integer range 10 10")
	void nextIndex_should_return_0_with_integer_range_10_10() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build(ModelBuilder.newEmptyModel().build());

		// WHEN
		Long result = nebulaRandom.nextIndex(range);

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("nextIndex should return index between 0 and 10 with integer range minus 1000 and minus 990")
	void nextIndex_should_return_index_between_0_and_10_with_integer_range_minus_1000_and_minus_990() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(0L);
		Type range = NebulaTypes.number().range().withMin(BigDecimal.valueOf(-1000))
				.withMax(BigDecimal.valueOf(-990)).build(ModelBuilder.newEmptyModel().build());

		// WHEN
		List<Long> result = new ArrayList<>();
		for (int i = 1; i <= 10000; i++) {
			result.add(nebulaRandom.nextIndex(range));
		}

		// THEN
		assertThat(result).containsAll(Arrays.asList(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L));
	}

	@Test
    @DisplayName("nextIndex should not throw exception when max bound is integer max and min bound is integer min")
	void nextIndex_should_not_throw_exception_when_max_bound_is_integer_max_and_min_bound_is_integer_min() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1L);
		Type type = mock(Type.class);
		long min = 0L;
		long max = 10L;
		when(type.getMinRange()).thenReturn(min);
		when(type.getMaxRange()).thenReturn(max);

		// WHEN
		Long result = nebulaRandom.nextIndex(type);

		// THEN
		assertThat(result).isBetween(min, max);
	}

	@Test
    @DisplayName("randomBetween should return 0")
	void randomBetween_should_return_0() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1L);

		// WHEN
		Long result = nebulaRandom.randomBetween(0L, 0L);

		// THEN
		assertThat(result).isEqualTo(0L);
	}

	@Test
    @DisplayName("randomBetween should return 1")
	void randomBetween_should_return_1() {

		// GIVEN
		NebulaRandom nebulaRandom = new NebulaRandom(1L);

		// WHEN
		Long result = nebulaRandom.randomBetween(1L, 1L);

		// THEN
		assertThat(result).isEqualTo(1L);
	}
}
