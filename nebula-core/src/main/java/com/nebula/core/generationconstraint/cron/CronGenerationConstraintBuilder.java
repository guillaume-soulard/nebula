package com.nebula.core.generationconstraint.cron;

import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.GenerationConstraintBuilder;

public class CronGenerationConstraintBuilder implements GenerationConstraintBuilder {

    @Override
    public GenerationConstraint build() {
        return new CronGenerationConstraint("0 * * ? * *");
    }
}
