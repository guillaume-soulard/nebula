package com.nebula.generationconstraint.custom;

import com.nebula.core.NebulaException;
import com.nebula.generationconstraint.GenerationConstraint;
import com.nebula.generationconstraint.amount.GenerationConstraintBuilder;

public class CustomGenerationConstraintBuilder implements GenerationConstraintBuilder {

    private GenerationConstraint constraint;

    public CustomGenerationConstraintBuilder(GenerationConstraint constraint) {
        if (constraint == null) {
            throw new NebulaException("constraint is null");
        }
        this.constraint = constraint;
    }

    @Override
    public GenerationConstraint build() {
        return constraint;
    }
}
