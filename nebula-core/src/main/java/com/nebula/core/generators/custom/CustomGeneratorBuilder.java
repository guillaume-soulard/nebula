package com.nebula.core.generators.custom;

import com.nebula.core.NebulaException;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class CustomGeneratorBuilder implements GeneratorBuilder {

    private Generator generator;

    public CustomGeneratorBuilder(Generator generator) {
        if (generator == null) {
            throw new NebulaException("generator is null");
        }
        this.generator = generator;
    }

    @Override
    public Generator build() {
        return generator;
    }
}
