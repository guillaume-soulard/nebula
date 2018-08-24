package com.nebula.core.generationconstraint.script;

import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.GenerationConstraintBuilder;

public class ScriptGenerationConstraintBuilder implements GenerationConstraintBuilder {

    private final String script;

    public ScriptGenerationConstraintBuilder(String script) {
        this.script = script;
    }

    @Override
    public GenerationConstraint build() {
        return new ScriptGenerationConstraint(script);
    }
}
