package com.nebula.core.generationconstraint.every;

import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.GenerationConstraintBuilder;

import java.util.concurrent.TimeUnit;

public class EveryGenerationConstraintBuilder implements GenerationConstraintBuilder {
    private long timeInMilli;

    public EveryGenerationConstraintBuilder(long duration, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new NebulaException("timeUnit is null");
        }
        this.timeInMilli = timeUnit.toMillis(duration);
    }

    @Override
    public GenerationConstraint build() {
        return new EveryGenerationConstraint(timeInMilli);
    }
}
