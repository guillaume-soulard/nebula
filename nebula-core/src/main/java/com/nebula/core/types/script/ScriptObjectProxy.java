package com.nebula.core.types.script;

public class ScriptObjectProxy {

    private Object object;

    public ScriptObjectProxy(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
