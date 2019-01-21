package com.nebula.core.generationconstraint.script;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScriptGenerationConstraintTest {

    @Test
    @DisplayName("accept should return ACCEPT")
    void accept_should_return_ACCEPT() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.accept()");
        GeneratedObject object = GeneratedObject.of(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    @DisplayName("accept should return REJECT")
    void accept_should_return_REJECT() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.reject()");
        GeneratedObject object = GeneratedObject.of(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.REJECT);
    }

    @Test
    @DisplayName("accept should return STOP GENERATION")
    void accept_should_return_STOP_GENERATION() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return nebula.stopGeneration()");
        GeneratedObject object = GeneratedObject.of(null);

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    @DisplayName("accept should throw exception when null is returned")
    void accept_should_throw_exception_when_null_is_returned() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return null");
        GeneratedObject object = GeneratedObject.of(null);
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
    @DisplayName("accept should throw exception when boolean is returned")
    void accept_should_throw_exception_when_boolean_is_returned() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return true");
        GeneratedObject object = GeneratedObject.of(null);
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
    @DisplayName("accept can access obj variable")
    void accept_can_access_obj_variable() {

        // GIVEN
        ScriptGenerationConstraint constraint = new ScriptGenerationConstraint("return obj == 'test' ? nebula.accept() : nebula.reject()");
        GeneratedObject object = GeneratedObject.of("test");

        // WHEN
        AcceptationResult result = constraint.accept(object);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }
}