package com.nebula.core.generationconstraint.amount;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountGenerationConstraintTest {

    @Test
    @DisplayName("new AmountGenerationConstraint should throw exception when maxAmountOfObjectToGenerate is less than 0")
    void new_AmountGenerationConstraint_should_throw_exception_when_maxAmountOfObjectToGenerate_is_less_than_0() {

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
    @DisplayName("accept should return ACCEPTABLE because it is first generated object and maxAmountOfObjectsToGenerate allow is 1")
    void accept_should_return_ACCEPTABLE_because_it_is_first_generated_object_and_maxAmountOfObjectsToGenerate_allow_is_1() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 1;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    @DisplayName("accept should return STOP GENERATION because no object is allowed")
    void accept_should_return_STOP_GENERATION_because_no_object_is_allowed() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 0;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    @DisplayName("accept should return STOP GENERATION because no more objects are allow")
    void accept_should_return_STOP_GENERATION_because_no_more_objects_are_allow() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 1;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    @DisplayName("accept should return ACCEPTABLE because more objects can be accept")
    void accept_should_return_ACCEPTABLE_because_more_objects_can_be_accept() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 10;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    @DisplayName("accept should return ACCEPTABLE because there is one more object to accept")
    void accept_should_return_ACCEPTABLE_because_there_is_one_more_object_to_accept() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 3;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    @DisplayName("accept should return STOP GENERATION because 3 objects are already been generated")
    void accept_should_return_STOP_GENERATION_because_3_objects_are_already_been_generated() {

        // GIVEN
        int maxAmountOfObjectsToGenerate = 3;
        AmountGenerationConstraint constraint = new AmountGenerationConstraint(maxAmountOfObjectsToGenerate);
        GeneratedObject generatedObject = GeneratedObject.of("");
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before
        constraint.accept(generatedObject); // one generation before

        // WHEN
        AcceptationResult result = constraint.accept(generatedObject);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }
}