package com.nebula.core.generationconstraint.during;

import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.GenerationConstraintBuilder;

import java.util.concurrent.TimeUnit;

public class DuringGenerationConstraintBuilder implements GenerationConstraintBuilder {

    private long timeToWaitAfterFirstItemGenerated;

    public DuringGenerationConstraintBuilder(long duration, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new NebulaException("timeUnit is null");
        }
        this.timeToWaitAfterFirstItemGenerated = timeUnit.toMillis(duration);
    }

    @Override
    public GenerationConstraint build() {
        return new DuringGenerationConstraint(timeToWaitAfterFirstItemGenerated);
    }
}
