package com.nebula.core.generationconstraint.cron;

import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CronGenerationConstraintTest {

    private final String cronExpression = "0 * * ? * *";

    @Test
    @DisplayName("new CronGenerationConstraint should set cron in field")
    void new_CronGenerationConstraint_should_set_cron_in_field() {

        GenerationConstraint constraint;

        // WHEN
        constraint = new CronGenerationConstraint(cronExpression);

        // THEN
        assertThat(constraint).hasFieldOrPropertyWithValue("cronExpression", cronExpression);
    }

    @Test
    @DisplayName("accept should return ACCEPTALE")
    void accept_should_return_ACCEPTALE() {

        // GIVEN
        GenerationConstraint constraint = new CronGenerationConstraint(cronExpression);

        // WHEN
        AcceptationResult result = constraint.accept(null);

        // THEN
        assertThat(result).isEqualTo(AcceptationResult.ACCEPT);
    }
}
