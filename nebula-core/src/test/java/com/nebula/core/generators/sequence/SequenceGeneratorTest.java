package com.nebula.core.generators.sequence;

import com.nebula.core.NebulaException;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SequenceGeneratorTest {

    @Test
    public void generate_should_call_generate_object_with_index_0() throws Exception {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 0L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(type);

        // THEN
        verify(type).generateObject(index);
    }

    @Test
    public void generate_should_call_generate_object_with_index_0_twice() throws Exception {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 0L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(type);
        generator.generate(type);

        // THEN
        verify(type, times(2)).generateObject(index);
    }

    @Test
    public void generate_should_call_generate_object_with_index_7_twice() throws Exception {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        when(type.getMaxRange()).thenReturn(10L);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 7L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(type);
        generator.generate(type);

        // THEN
        verify(type, times(2)).generateObject(index);
    }

    @Test
    public void generate_should_throw_exception_when_index_reach_max_range() throws Exception {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        when(type.getMaxRange()).thenReturn(10L);
        when(type.getMinRange()).thenReturn(0L);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 17L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        catchException(generator).generate(type);

        // THEN
        assertThat((Exception) caughtException())
                .isInstanceOf(NebulaException.class)
                .hasMessage("sequence reach the maximum index of type (11). Use cycle() to allow sequence to restart");
    }

    @Test
    public void generate_should_not_throw_exception_when_index_reach_max_range_because_cycle_is_true() throws Exception {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(true);
        Type type = mock(Type.class);
        when(type.getMaxRange()).thenReturn(10L);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 17L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        catchException(generator).generate(type);

        // THEN
        assertThat((Exception) caughtException()).isNull();
    }
}