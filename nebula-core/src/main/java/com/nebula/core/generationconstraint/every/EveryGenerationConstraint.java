package com.nebula.core.generationconstraint.every;

import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;

public class EveryGenerationConstraint implements GenerationConstraint {

    private final long timeInMilli;

    EveryGenerationConstraint(long timeInMilli) {

        this.timeInMilli = timeInMilli;
    }

    @Override
    public AcceptationResult accept(GeneratedObject generatedObject) {
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            throw new NebulaException(e.getMessage());
        }
        return AcceptationResult.ACCEPT;
    }
}
