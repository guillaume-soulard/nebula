package com.nebula.core.generationconstraint.script;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScriptGenerationConstraintTest {

    @Test
    void accept_should_return_ACCEPT() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.accept()");
        GeneratedObject object = new GeneratedObject(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    void accept_should_return_REJECT() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.reject()");
        GeneratedObject object = new GeneratedObject(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.REJECT);
    }

    @Test
    void accept_should_return_STOP_GENERATION() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.stopGeneration()");
        GeneratedObject object = new GeneratedObject(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    void accept_should_throw_exception_when_null_is_returned() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return null");
        GeneratedObject object = new GeneratedObject(null);
        Exception exception = null;

        // WHEN
        try {
            constraint.accept(object);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class)
                .hasMessage("Script constraint must return nebula.accept() or nebula.reject() or nebula.stopGeneration()");
    }

    @Test
    void accept_should_throw_exception_when_boolean_is_returned() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return true");
        GeneratedObject object = new GeneratedObject(null);
        Exception exception = null;

        // WHEN
        try {
            constraint.accept(object);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class)
                .hasMessage("Script constraint must return nebula.accept() or nebula.reject() or nebula.stopGeneration()");
    }

    @Test
    void accept_can_access_obj_variable() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return obj == 'test' ? nebula.accept() : nebula.reject()");
        GeneratedObject object = new GeneratedObject("test");

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }
}