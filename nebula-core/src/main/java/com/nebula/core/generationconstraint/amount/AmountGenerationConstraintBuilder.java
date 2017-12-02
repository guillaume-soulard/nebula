package com.nebula.core.generationconstraint.amount;

import com.nebula.core.generationconstraint.GenerationConstraint;

public class AmountGenerationConstraintBuilder implements GenerationConstraintBuilder {

    private final int maxObjectsToGenerate;

    public AmountGenerationConstraintBuilder(int maxObjectsToGenerate) {
        this.maxObjectsToGenerate = maxObjectsToGenerate;
    }

    @Override
    public GenerationConstraint build() {
        return new AmountGenerationConstraint(maxObjectsToGenerate);
    }
}
