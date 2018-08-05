package com.nebula.benchmark.generator;

import com.nebula.core.generators.GeneratorBuilder;

import static com.nebula.core.generators.NebulaGenerators.randomUnique;

public class RandomUniquekBenchmark extends AbstractGeneratorBenchmark {

    @Override
    protected GeneratorBuilder getGenerator() {
        return randomUnique();
    }
}
