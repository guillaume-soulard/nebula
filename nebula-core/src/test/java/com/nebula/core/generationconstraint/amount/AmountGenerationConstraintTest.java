package com.nebula.core.generationconstraint.amount;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountGenerationConstraintTest {

    @Test
    public void new_AmountGenerationConstraint_should_throw_exception_when_maxAmountOfObjectToGenerate_is_less_than_0() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = -1;
        Exception exception = null;

        // WHEN
        try {
            new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertThat(exception).isInstanceOf(NebulaException.class).hasMessage("maxAmountOfObjectsToGenerate is negative");
    }

    @Test
    public void accept_should_return_ACCEPTABLE_because_it_is_first_generated_object_and_maxAmountOfObjectsToGenerate_allow_is_1() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 1;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }

    @Test
    public void accept_should_return_STOP_GENERATION_because_no_object_is_allowed() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 0;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    public void accept_should_return_STOP_GENERATION_because_no_more_objects_are_allow() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 1;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    public void accept_should_return_ACCEPTABLE_because_more_objects_can_be_accept() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 10;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }

    @Test
    public void accept_should_return_ACCEPTABLE_because_there_is_one_more_object_to_accept() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 3;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }

    @Test
    public void accept_should_return_STOP_GENERATION_because_3_objects_are_already_been_generated() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 3;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = new GeneratedObject("");
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }
}