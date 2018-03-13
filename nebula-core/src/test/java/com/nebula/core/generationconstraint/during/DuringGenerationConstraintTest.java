package com.nebula.core.generationconstraint.during;

import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DuringGenerationConstraintTest {

    private static final long ONE_SECOND = 1000L;

    @Test
    public void accept_should_not_return_null() {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);
        constraint.accept(null);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isNotNull();
    }

    @Test
    public void accept_should_return_ACCEPTABLE_if_time_to_wait_is_not_reached() {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);
        constraint.accept(null);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }

    @Test
    public void accept_should_return_STOP_GENERATION_if_time_to_wait_is_reached() throws InterruptedException {

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
    public void accept_should_return_ACCEPTABLE_if_time_is_reached_but_first_accept_not_invoked() throws InterruptedException {

        // GIVEN
        GenerationConstraint constraint  = new DuringGenerationConstraint(ONE_SECOND);

        // WHEN
        Thread.sleep(ONE_SECOND + ONE_SECOND);
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }
}
