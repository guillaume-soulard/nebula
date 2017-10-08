package com.nebula.generationconstraint.amount;


import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.generationconstraint.GenerationConstraint;

import java.util.concurrent.atomic.AtomicInteger;

public class AmountGenerationConstraint implements GenerationConstraint {

    private final int maxAmountOfObjectsToGenerate;
    private AtomicInteger amountOfAlreadyGeneratedObjects = new AtomicInteger(0);


    public AmountGenerationConstraint(int maxAmountOfObjectsToGenerate) {

        if (maxAmountOfObjectsToGenerate < 0) {
            throw new NebulaException("maxAmountOfObjectsToGenerate is negative");
        }
        this.maxAmountOfObjectsToGenerate = maxAmountOfObjectsToGenerate;
    }

    @Override
    public boolean accept(GeneratedObject generatedObject) {
        boolean acceptable = amountOfAlreadyGeneratedObjects.get() + 1 <= maxAmountOfObjectsToGenerate;

        if (acceptable) {
            amountOfAlreadyGeneratedObjects.incrementAndGet();
        }

        return acceptable;
    }
}
