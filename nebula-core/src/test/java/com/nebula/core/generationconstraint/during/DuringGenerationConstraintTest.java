package com.nebula.core.generationconstraint.during;

import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DuringGenerationConstraintTest {

    private static final long ONE_SECOND = 1000L;

    @Test
    @DisplayName("accept should not return null")
    void accept_should_not_return_null() {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);
        constraint.accept(null);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("accept should return ACCEPTABLE if time to wait is not reached")
    void accept_should_return_ACCEPTABLE_if_time_to_wait_is_not_reached() {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);
        constraint.accept(null);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }

    @Test
    @DisplayName("accept should return STOP GENERATION if time to wait is reached")
    void accept_should_return_STOP_GENERATION_if_time_to_wait_is_reached() throws InterruptedException {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);
        constraint.accept(null);

        // WHEN
        Thread.sleep(ONE_SECOND + ONE_SECOND);
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.STOP_GENERATION);
    }

    @Test
    @DisplayName("accept should return ACCEPTABLE if time is reached but first accept not invoked")
    void accept_should_return_ACCEPTABLE_if_time_is_reached_but_first_accept_not_invoked() throws InterruptedException {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);

        // WHEN
        Thread.sleep(ONE_SECOND + ONE_SECOND);
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }
}
