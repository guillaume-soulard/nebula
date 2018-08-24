package com.nebula.core.types.script;

import com.nebula.core.Model;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class ScriptTypeBuilder implements TypeBuilder {

    private final String script;

    public ScriptTypeBuilder(String script) {
        this.script = script;
    }

    @Override
    public Type build(Model model) {
        return new ScriptType(script);
    }
}
