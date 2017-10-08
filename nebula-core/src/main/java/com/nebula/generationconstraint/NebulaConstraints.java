package com.nebula.generationconstraint;

import com.nebula.generationconstraint.amount.AmountGenerationConstraintBuilder;

public final class NebulaConstraints {

    public static AmountGenerationConstraintBuilder amount(int maxObjectsToGenerate) {
        return new AmountGenerationConstraintBuilder(maxObjectsToGenerate);
    }
}
