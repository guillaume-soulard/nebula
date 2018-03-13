package com.nebula.core.generationconstraint.every;

import com.nebula.core.generationconstraint.AcceptationResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EveryGenerationConstraintTest {

    private static final long ONE_SECOND = 1000L;

    @Test
    public void accept_should_not_return_ACCEPTABLE() {

        // GIVEN
        EveryGenerationConstraint constraint = new EveryGenerationConstraint(ONE_SECOND);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }

    @Test
    public void accept_should_wait_1_second() {

        // GIVEN
        EveryGenerationConstraint constraint = new EveryGenerationConstraint(ONE_SECOND);
        long startTime = System.currentTimeMillis();

        // WHEN
        constraint.accept(null);

        // THEN
        long endTime = System.currentTimeMillis();
        assertThat(endTime - startTime).isBetween(ONE_SECOND, ONE_SECOND + 100L);
    }
}