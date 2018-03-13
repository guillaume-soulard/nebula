package com.nebula.core.types.custom;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;

public class CustomTypeBuilder implements RandomTypeBuilder {
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
