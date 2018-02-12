package com.nebula.core.generators.randomunique;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;

public class RandomUniqueGeneratorBuilder implements GeneratorBuilder {
    @Override
    public Generator build() {
        return new RandomUniqueGenerator();
    }
}
