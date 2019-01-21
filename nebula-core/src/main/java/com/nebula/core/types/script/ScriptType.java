package com.nebula.core.types.script;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.Type;
import org.apache.commons.jexl3.*;

public class ScriptType implements Type {

    private final JexlScript script;
    private ScriptTypeObjectHelper scriptTypeObjectHelper;

    ScriptType(String scriptString) {

        JexlEngine jexl = new JexlBuilder().create();
        script = jexl.createScript(scriptString);
    }

    @Override
    public void init(GenerationContext context) {

        scriptTypeObjectHelper = new ScriptTypeObjectHelper(context.getModel());
    }

    @Override
    public GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex) {

        JexlContext context = new MapContext();
        ObservableMap<String, Object> map = new ObservableMap<>();

        for (GeneratedProperty generatedProperty : generatedProperties.getGeneratedProperties()) {
            map.put(generatedProperty.getPropertyName(), generatedProperty.getPropertyValue().getObject());
        }

        context.set("self", map);
        context.set("nebula", scriptTypeObjectHelper);
        Object result = script.execute(context);

        if (result != null && ScriptObjectProxy.class.equals(result.getClass())) {
            return GeneratedObject.of(((ScriptObjectProxy) result).getObject());
        } else {
            throw new NebulaException("Wrong return type for script. Use nebula.number(<number>), nebula.date(<dateString>, <format>) nebula.boolTrue() nebula.boolFalse() nebula.string(<string>) nebula.nil() instead");
        }
    }

    @Override
    public Long getMinRange() {
        return 0L;
    }

    @Override
    public Long getMaxRange() {
        return 0L;
    }

    @Override
    public JavaType getJavaType() {
        return JavaType.STRING;
    }
}
