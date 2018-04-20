package com.nebula.core.generators.randomunique;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.types.Type;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RandomUniqueGeneratorTest {

    @Test
    public void generate_should_not_return_null() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());
        when(type.generateObject(anyLong())).thenReturn(mock(GeneratedObject.class));

        // WHEN
        GeneratedObject result = generator.generate(type);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void generate_should_throw_NebulaException_when_is_not_initialized() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildGenerator();

        // WHEN
        catchException(generator).generate(type);

        // THEN
        assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("generator is not initialized");
    }

    @Test
    public void generate_should_not_throw_NebulaException_when_it_is_initialized() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        catchException(generator).generate(type);

        // THEN
        assertThat((Exception) caughtException()).isNull();
    }

    @Test
    public void generate_should_init_type() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        generator.generate(type);

        // THEN
        verify(type).init(any(GenerationContext.class));
    }

    @Test
    public void generate_should_return_generated_object_by_type() {

        // GIVEN
        Type type = buildType();
        Generator generator = buildAndInitGenerator(buildGenerationContext());
        GeneratedObject generatedObject = mock(GeneratedObject.class);
        when(type.generateObject(anyLong())).thenReturn(generatedObject);

        // WHEN
        GeneratedObject generate = generator.generate(type);

        // THEN
        assertThat(generate).isEqualTo(generatedObject);
    }

    @Test
    public void generate_should_call_generate_object_with_all_10_indices_randomly_when_generate_is_called_10_times() {

        // GIVEN
        Type type = spy(NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(mock(Model.class)));
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        callGenerateNTimes(type, generator, 10);

        // THEN
        verify(type
        ).generateObject(0L);
        verify(type).generateObject(1L);
        verify(type).generateObject(2L);
        verify(type).generateObject(3L);
        verify(type).generateObject(4L);
        verify(type).generateObject(5L);
        verify(type).generateObject(6L);
        verify(type).generateObject(7L);
        verify(type).generateObject(8L);
        verify(type).generateObject(9L);
        verify(type).generateObject(10L);
    }

    @Test
    public void generate_should_call_generate_object_with_all_10_indices_randomly_when_generate_is_called_20_times() {

        // GIVEN
        Type type = spy(NebulaTypes.number().range().withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).build(mock(Model.class)));
        Generator generator = buildAndInitGenerator(buildGenerationContext());

        // WHEN
        callGenerateNTimes(type, generator, 21);

        // THEN
        verify(type, times(2)).generateObject(0L);
        verify(type, times(2)).generateObject(1L);
        verify(type, times(2)).generateObject(2L);
        verify(type, times(2)).generateObject(3L);
        verify(type, times(2)).generateObject(4L);
        verify(type, times(2)).generateObject(5L);
        verify(type, times(2)).generateObject(6L);
        verify(type, times(2)).generateObject(7L);
        verify(type, times(2)).generateObject(8L);
        verify(type, times(2)).generateObject(9L);
        verify(type, times(2)).generateObject(10L);
    }

    private GenerationContext buildGenerationContext() {
        return new GenerationContext(new NebulaRandom(1L), mock(Model.class), 0L, 1, 10);
    }

    private void callGenerateNTimes(Type type, Generator generator, int numberOfGenerateCallToProcess) {
        IntStream.rangeClosed(0, numberOfGenerateCallToProcess).forEach((int i) -> generator.generate(type));
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
