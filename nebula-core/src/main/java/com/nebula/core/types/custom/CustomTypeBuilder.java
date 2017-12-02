package com.nebula.core.types.custom;

import com.nebula.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class CustomTypeBuilder implements TypeBuilder {
    private Type type;

    public CustomTypeBuilder(Type type) {
        if (type == null) {
            throw new NebulaException("type is null");
        }
        this.type = type;
    }

    @Override
    public Type build(Model model) {
        return type;
    }
}
