package com.nebula.generationconstraint.amount;


import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.generationconstraint.AcceptationResult;
import com.nebula.generationconstraint.GenerationConstraint;

import java.util.concurrent.atomic.AtomicInteger;

class AmountGenerationConstraint implements GenerationConstraint {

    private final int maxAmountOfObjectsToGenerate;
    private AtomicInteger amountOfAlreadyGeneratedObjects = new AtomicInteger(0);


    AmountGenerationConstraint(int maxAmountOfObjectsToGenerate) {

        if (maxAmountOfObjectsToGenerate < 0) {
            throw new NebulaException("maxAmountOfObjectsToGenerate is negative");
        }
        this.maxAmountOfObjectsToGenerate = maxAmountOfObjectsToGenerate;
    }

    @Override
    public AcceptationResult accept(GeneratedObject generatedObject) {
        AcceptationResult acceptable = AcceptationResult.ACCEPTABLE;

        if (amountOfAlreadyGeneratedObjects.get() + 1 <= maxAmountOfObjectsToGenerate) {
            amountOfAlreadyGeneratedObjects.incrementAndGet();
        } else {
            acceptable = AcceptationResult.STOP_GENERATION;
        }

        return acceptable;
    }
}
