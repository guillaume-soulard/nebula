package com.nebula.core.generationconstraint.script;

import com.nebula.core.generationconstraint.AcceptationResult;

public class ScriptGenerationConstraintHelper {

    public ScriptGenerationConstraintProxy accept() {

        return new ScriptGenerationConstraintProxy(AcceptationResult.ACCEPT);
    }

    public ScriptGenerationConstraintProxy reject() {

        return new ScriptGenerationConstraintProxy(AcceptationResult.REJECT);
    }

    public ScriptGenerationConstraintProxy stopGeneration() {

        return new ScriptGenerationConstraintProxy(AcceptationResult.STOP_GENERATION);
    }
}
