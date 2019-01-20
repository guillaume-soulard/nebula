package com.nebula.core.generators.sequence;

import com.nebula.core.NebulaException;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class SequenceGeneratorTest {

    @Test
    @DisplayName("generate should call generate object with index 0")
    void generate_should_call_generate_object_with_index_0() {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 0L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        verify(type).generateObject(new GeneratedProperties(Collections.emptyList()), index);
    }

    @Test
    @DisplayName("generate should call generate object with index 0 twice")
    void generate_should_call_generate_object_with_index_0_twice() {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 0L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), index);
    }

    @Test
    @DisplayName("generate should call generate object with index 7 twice")
    void generate_should_call_generate_object_with_index_7_twice() {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(false);
        Type type = mock(Type.class);
        when(type.getMaxRange()).thenReturn(10L);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 7L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);
        generator.generate(new GeneratedProperties(Collections.emptyList()), type);

        // THEN
        verify(type, times(2)).generateObject(new GeneratedProperties(Collections.emptyList()), index);
    }

    @Test
    @DisplayName("generate should throw exception when index reach max range")
    void generate_should_throw_exception_when_index_reach_max_range() {

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

        // THEN
        assertThatThrownBy(() -> generator.generate(new GeneratedProperties(Collections.emptyList()), type))
                .isInstanceOf(NebulaException.class)
                .hasMessage("sequence reach the maximum index of type (11). Use cycle() to allow sequence to restart");
    }

    @Test
    @DisplayName("generate should not throw exception when index reach max range because cycle is true")
    void generate_should_not_throw_exception_when_index_reach_max_range_because_cycle_is_true() {

        // GIVEN
        SequenceGenerator generator = new SequenceGenerator(true);
        Type type = mock(Type.class);
        when(type.getMaxRange()).thenReturn(10L);
        GenerationContext context = mock(GenerationContext.class);
        generator.init(context);
        long index = 17L;
        when(context.getEntityIndex()).thenReturn(index);

        // WHEN

        // THEN
        Assertions.assertDoesNotThrow(() -> {
            generator.generate(new GeneratedProperties(Collections.emptyList()), type);
        });
    }
}