package com.nebula.core.generators.random;

import com.nebula.core.GeneratedObject;
import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class RandomGeneratorTest {

	@Test
    @DisplayName("random should return a new instance of RandomGenerator")
	void random_should_return_a_new_instance_of_RandomGenerator() {

		// GIVEN

		// WHEN
		RandomGenerator generator = new RandomGenerator();

		// THEN
		assertThat(generator).isNotNull();
	}

	@Test
    @DisplayName("generate should return one")
	void generate_should_return_one() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(mock(NebulaRandom.class), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.ONE).withMax(BigDecimal.ONE).build(ModelBuilder.newEmptyModel().build());

		// WHEN
        GeneratedObject result = generator.generate(Collections.emptyList(), type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.ONE);
	}

	@Test
    @DisplayName("generate should return ten")
	void generate_should_return_ten() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(mock(NebulaRandom.class), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.TEN).withMax(BigDecimal.TEN).build(ModelBuilder.newEmptyModel().build());

		// WHEN
        GeneratedObject result = generator.generate(Collections.emptyList(), type);

		// THEN
		assertThat(result.getObject()).isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.TEN);
	}

	@Test
    @DisplayName("generate should return a number between 0 and 10")
	void generate_should_return_a_number_between_0_and_10() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		long entityIndex = 0L;
		generator.init(new GenerationContext(new NebulaRandom(0L), null, entityIndex, 1, 10));
		Type type = NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(ModelBuilder.newEmptyModel().build());

		// WHEN
		List<GeneratedObject> result = new ArrayList<>();

		for (int i = 1; i <= 10000; i++) {
            result.add(generator.generate(Collections.emptyList(), type));
		}

		// THEN
		assertThat(result).extracting("object")
				.containsAll(Arrays.asList(BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(2),
						BigDecimal.valueOf(3), BigDecimal.valueOf(4), BigDecimal.valueOf(5), BigDecimal.valueOf(6),
						BigDecimal.valueOf(7), BigDecimal.valueOf(8), BigDecimal.valueOf(9), BigDecimal.valueOf(10)));
	}

	@Test
    @DisplayName("init should throw exception when nebulaRandom is null")
	void init_should_throw_exception_when_nebulaRandom_is_null() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();

		// WHEN

		// THEN
        assertThatThrownBy(() -> generator.init(null))
                .isInstanceOf(NebulaException.class)
				.hasMessage("generationContext is null");
	}

	@Test
    @DisplayName("generate should throw exception when nebula random is null")
	void generate_should_throw_exception_when_nebula_random_is_null() {

		// GIVEN
		RandomGenerator generator = new RandomGenerator();
		Type type = NebulaTypes.number().range().build(ModelBuilder.newEmptyModel().build());

		// WHEN

		// THEN
        assertThatThrownBy(() -> generator.generate(Collections.emptyList(), type))
                .isInstanceOf(NebulaException.class)
				.hasMessage("generationContext is null");
	}
}
