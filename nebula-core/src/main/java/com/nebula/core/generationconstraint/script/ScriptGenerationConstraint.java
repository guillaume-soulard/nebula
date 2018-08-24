package com.nebula.core.generationconstraint.script;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.generationconstraint.AcceptationResult;
import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.types.script.ObservableMap;
import org.apache.commons.jexl3.*;

public class ScriptGenerationConstraint implements GenerationConstraint {

    private final JexlScript script;
    private final ScriptGenerationConstraintHelper scriptGenerationConstraintHelper;

    ScriptGenerationConstraint(String scriptString) {

        JexlEngine jexl = new JexlBuilder().create();
        script = jexl.createScript(scriptString);
        scriptGenerationConstraintHelper = new ScriptGenerationConstraintHelper();
    }

    @Override
    public AcceptationResult accept(GeneratedObject generatedObject) {
        JexlContext context = new MapContext();
        ObservableMap<String, Object> map = new ObservableMap<>();

        if (generatedObject.getGeneratedProperties() != null) {
            for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {

                map.put(generatedProperty.getPropertyName(), generatedProperty.getPropertyValue().getObject());
            }

            context.set("obj", map);
        } else {
            context.set("obj", generatedObject.getObject());
        }

        context.set("nebula", scriptGenerationConstraintHelper);

        Object result = script.execute(context);

        if (result == null || !ScriptGenerationConstraintProxy.class.equals(result.getClass())) {

            throw new NebulaException("Script constraint must return nebula.accept() or nebula.reject() or nebula.stopGeneration()");
        }

        return ((ScriptGenerationConstraintProxy) result).getAcceptationResult();
    }
}
