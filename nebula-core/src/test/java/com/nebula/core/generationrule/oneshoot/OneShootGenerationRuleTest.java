package com.nebula.core.generationrule.oneshoot;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedObjectIterator;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OneShootGenerationRuleTest {

    @Test
    void new_GenerationRule_should_set_correctly_properties() {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Collections.singletonList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        OneShootGenerationRule generationRule;

        // WHEN
        generationRule = new OneShootGenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());

        // THEN
        assertThat(generationRule).hasFieldOrPropertyWithValue("outputsToWrite", outputs)
            .hasFieldOrPropertyWithValue("formatterToUse", formatter)
            .hasFieldOrPropertyWithValue("generatedObjectSource", generatedObjectSource)
            .hasFieldOrPropertyWithValue("entity", entity);
    }

    @Test
    void new_GenerationRule_should_throw_exception_when_entity_is_null() {

        // GIVEN
        Output output = mock(Output.class);
        List<Output> outputs = Collections.singletonList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(null);
        Exception exception = null;

        // WHEN
        try {
            new OneShootGenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entity is null");
    }

    @Test
    void new_GenerationRule_should_throw_exception_when_outputs_is_null() {

        // GIVEN
        Entity entity = mock(Entity.class);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new OneShootGenerationRule(null, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("outputs is null");
    }

    @Test
    void new_GenerationRule_should_throw_exception_when_outputs_contains_a_null() {

        // GIVEN
        Entity entity = mock(Entity.class);
        List<Output> outputs = Collections.singletonList(null);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new OneShootGenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("outputs contains null");
    }

    @Test
    void new_GenerationRule_should_throw_exception_when_formatter_null() {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Collections.singletonList(output);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new OneShootGenerationRule(outputs, null, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("formatter is null");
    }

    @Test
    void new_GenerationRule_should_throw_exception_when_generatedObjectIterator_null() {

        // GIVEN
        Output output = mock(Output.class);
        List<Output> outputs = Collections.singletonList(output);
        Formatter formatter = mock(Formatter.class);
        Exception exception = null;

        // WHEN
        try {
            new OneShootGenerationRule(outputs, formatter, null, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("generatedObjectSource is null");
    }

    @Test
    void generate_should_call_objects_in_right_order() {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Collections.singletonList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.hasNext()).thenReturn(true).thenReturn(false);
        GeneratedObject generatedObject = mock(GeneratedObject.class);
        when(generatedObjectSource.next()).thenReturn(generatedObject);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        OneShootGenerationRule generationRule = new OneShootGenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());

        // WHEN
        generationRule.generate();

        // THEN
        InOrder inOrder = inOrder(formatter, output, generatedObjectSource);

        inOrder.verify(formatter).formatHeader(entity);
        inOrder.verify(output, times(2)).write(any(OutputParameter.class));
        inOrder.verify(generatedObjectSource).hasNext();
        inOrder.verify(generatedObjectSource).next();
        inOrder.verify(formatter).formatGeneratedObject(generatedObject);
        inOrder.verify(output, times(2)).write(any(OutputParameter.class));
        inOrder.verify(formatter).formatFooter(entity);
    }
}