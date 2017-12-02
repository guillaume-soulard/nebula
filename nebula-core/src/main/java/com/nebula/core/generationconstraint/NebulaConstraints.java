package com.nebula.core.generationconstraint;

import com.nebula.core.generationconstraint.amount.AmountGenerationConstraintBuilder;
import com.nebula.core.generationconstraint.custom.CustomGenerationConstraintBuilder;

public final class NebulaConstraints {

    public static AmountGenerationConstraintBuilder amount(int maxObjectsToGenerate) {
        return new AmountGenerationConstraintBuilder(maxObjectsToGenerate);
    }

    public static CustomGenerationConstraintBuilder custom(GenerationConstraint generationConstraint) {
        return new CustomGenerationConstraintBuilder(generationConstraint);
    }
}
