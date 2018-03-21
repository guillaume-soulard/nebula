package com.nebula.core.generationconstraint.cron;

import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.GenerationConstraintBuilder;

public class CronGenerationConstraintBuilder implements GenerationConstraintBuilder {

    private String cronExpression = "0 * * ? * *";

    @Override
    public GenerationConstraint build() {
        return new CronGenerationConstraint(cronExpression);
    }
}
