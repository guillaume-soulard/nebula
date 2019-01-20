package com.nebula.core.generators.randomunique;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RandomUniqueGeneratorTest {

    @Test
    @DisplayName("generate should not return null")
    void generate_should_not_return_null() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());
        when(type.generateObject(any(), anyLong())).thenReturn(mock(GeneratedObject.class));

        // WHEN
        GeneratedObject result = generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("generate should throw NebulaException when is not initialized")
    void generate_should_throw_NebulaException_when_is_not_initialized() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildGenerator();

        // WHEN

        // THEN
        assertThatThrownBy(() -> generator.generate(new GeneratedProperties(Collections.emptyList()), type))
                .isInstanceOf(NebulaException.class)
                .hasMessage("generator is not initialized");
    }

    @Test
    @DisplayName("generate should not throw NebulaException when it is initialized")
    void generate_should_not_throw_NebulaException_when_it_is_initialized() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN

        // THEN
        Assertions.assertDoesNotThrow(() -> {
            generator.generate(new GeneratedProperties(Collections.emptyList()), type);
        });
    }

    @Test
    @DisplayName("generate should init type")
    void generate_should_init_type() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        verify(type).init(any(GenerationContext.class));
    }

    @Test
    @DisplayName("generate should return generated object by type")
    void generate_should_return_generated_object_by_type() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());
        GeneratedObject generatedObject = mock(GeneratedObject.class);
        when(type.generateObject(any(), anyLong())).thenReturn(generatedObject);

        // WHEN
        GeneratedObject generate = generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        assertThat(generate).isEqualTo(generatedObject);
    }

    @Test
    @DisplayName("generate should call generate object with all 10 indices randomly when generate is called 10 times")
    void generate_should_call_generate_object_with_all_10_indices_randomly_when_generate_is_called_10_times() {

        // GIVEN
        Type type = spy(NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(mock(Model.class)));
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        callGenerateNTimes(type, generator, 10);

        // THEN
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 0L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 1L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 2L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 3L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 4L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 5L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 6L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 7L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 8L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 9L);
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), 10L);
    }

    @Test
    @DisplayName("generate should call generate object with all 10 indices randomly when generate is called 20 times")
    void generate_should_call_generate_object_with_all_10_indices_randomly_when_generate_is_called_20_times() {

        // GIVEN
        Type type = spy(NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(mock(Model.class)));
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        callGenerateNTimes(type, generator, 21);

        // THEN
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 0L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 1L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 2L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 3L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 4L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 5L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 6L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 7L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 8L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 9L);
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), 10L);
    }

    private GenerationContext buildGenerationContext() {
        return new GenerationContext(new NebulaRandom(1L), mock(Model.class), 0L, 1, 10);
    }

    private void callGenerateNTimes(Type type, Generator generator, int numberOfGenerateCallToProcess) {
        IntStream.rangeClosed(0, numberOfGenerateCallToProcess).forEach((int i) -> generator.generate(new GeneratedProperties(Collections.emptyList()), type));
    }

    private Type buildType() {
        return mock(Type.class);
    }

    private Generator buildAndInitGenerator(GenerationContext context) {
        Generator generator = buildGenerator();
        generator.init(context);
        return generator;
    }

    private Generator buildGenerator() {
        return new RandomUniqueGenerator();
    }
}
