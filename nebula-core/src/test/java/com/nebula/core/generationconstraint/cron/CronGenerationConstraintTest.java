package com.nebula.core.generationconstraint.cron;

import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CronGenerationConstraintTest {

    private String cronExpression = "0 * * ? * *";

    @Test
    public void new_CronGenerationConstraint_should_set_cron_in_field() {

        GenerationConstraint constraint;

        // WHEN
        constraint = new CronGenerationConstraint(cronExpression);

        // THEN
        assertThat(constraint).hasFieldOrPropertyWithValue("cronExpression", cronExpression);
    }

    @Test
    public void accept_should_return_ACCEPTALE() {

        // GIVEN
        GenerationConstraint constraint = new CronGenerationConstraint(cronExpression);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPTABLE);
    }
}
