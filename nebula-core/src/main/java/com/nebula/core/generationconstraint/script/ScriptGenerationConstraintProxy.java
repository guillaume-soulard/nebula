package com.nebula.core.generationconstraint.script;

import com.nebula.core.generationconstraint.AcceptationResult;

public class ScriptGenerationConstraintProxy {

    private AcceptationResult acceptationResult;

    public ScriptGenerationConstraintProxy(AcceptationResult acceptationResult) {
        this.acceptationResult = acceptationResult;
    }

    public AcceptationResult getAcceptationResult() {
        return acceptationResult;
    }
}
