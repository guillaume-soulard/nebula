package com.nebula.core.generationconstraint.during;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;

public class DuringGenerationConstraint implements GenerationConstraint {

    private final long timeToWaitInMilli;
    private long startTime = -1;

    DuringGenerationConstraint(long timeToWaitInMilli) {

        this.timeToWaitInMilli = timeToWaitInMilli;
    }

    @Override
    public AcceptationResult accept(GeneratedObject generatedObject) {

        if (startTime == -1) {
            startTime = System.currentTimeMillis();
        }

        if (startTime + timeToWaitInMilli < System.currentTimeMillis()) {
            return AcceptationResult.STOP_GENERATION;
        }

        return AcceptationResult.ACCEPT;
    }
}