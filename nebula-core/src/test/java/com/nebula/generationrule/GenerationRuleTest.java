package com.nebula.generationrule;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedObjectIterator;
import com.nebula.core.NebulaException;
import com.nebula.formatter.Formatter;
import com.nebula.output.Output;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GenerationRuleTest {

    @Test
    public void new_GenerationRule_should_set_correctly_properties() throws Exception {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        GenerationRule generationRule;

        // WHEN
        generationRule = new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());

        // THEN
        assertThat(generationRule).hasFieldOrPropertyWithValue("outputsToWrite", outputs)
            .hasFieldOrPropertyWithValue("formatterToUse", formatter)
            .hasFieldOrPropertyWithValue("generatedObjectSource", generatedObjectSource)
            .hasFieldOrPropertyWithValue("entity", entity);
    }

    @Test
    public void new_GenerationRule_should_throw_exception_when_entity_is_null() throws Exception {

        // GIVEN
        Entity entity = null;
        Output output = mock(Output.class);
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("entity is null");
    }

    @Test
    public void new_GenerationRule_should_throw_exception_when_outputs_is_null() throws Exception {

        // GIVEN
        Entity entity = mock(Entity.class);
        List<Output> outputs = null;
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("outputs is null");
    }

    @Test
    public void new_GenerationRule_should_throw_exception_when_outputs_contains_a_null() throws Exception {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = null;
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("outputs contains null");
    }

    @Test
    public void new_GenerationRule_should_throw_exception_when_formatter_null() throws Exception {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = null;
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        Exception exception = null;

        // WHEN
        try {
            new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("formatter is null");
    }

    @Test
    public void new_GenerationRule_should_throw_exception_when_generatedObjectIterator_null() throws Exception {

        // GIVEN
        Output output = mock(Output.class);
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = null;
        Exception exception = null;

        // WHEN
        try {
            new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("generatedObjectSource is null");
    }

    @Test
    public void generate_should_call_objects_in_right_order() throws Exception {

        // GIVEN
        Entity entity = mock(Entity.class);
        Output output = mock(Output.class);
        List<Output> outputs = Arrays.asList(output);
        Formatter formatter = mock(Formatter.class);
        GeneratedObjectIterator generatedObjectSource = mock(GeneratedObjectIterator.class);
        when(generatedObjectSource.hasNext()).thenReturn(true).thenReturn(false);
        GeneratedObject generatedObject = mock(GeneratedObject.class);
        when(generatedObjectSource.next()).thenReturn(generatedObject);
        when(generatedObjectSource.getEntity()).thenReturn(entity);
        GenerationRule generationRule = new GenerationRule(outputs, formatter, generatedObjectSource, new ArrayList<>());

        // WHEN
        generationRule.generate();

        // THEN
        InOrder inOrder = inOrder(formatter, output, generatedObjectSource);

        inOrder.verify(formatter).formatHeader(entity);
        inOrder.verify(output, times(2)).write(any(String.class));
        inOrder.verify(generatedObjectSource).hasNext();
        inOrder.verify(generatedObjectSource).next();
        inOrder.verify(formatter).formatGeneratedObject(generatedObject);
        inOrder.verify(output, times(2)).write(any(String.class));
        inOrder.verify(formatter).formatFooter(entity);
    }
}