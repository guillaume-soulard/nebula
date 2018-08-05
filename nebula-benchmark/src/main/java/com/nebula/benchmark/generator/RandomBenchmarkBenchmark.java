package com.nebula.benchmark.generator;

import com.nebula.core.generators.GeneratorBuilder;

import static com.nebula.core.generators.NebulaGenerators.random;

public class RandomBenchmarkBenchmark extends AbstractGeneratorBenchmark {

    @Override
    protected GeneratorBuilder getGenerator() {
        return random();
    }
}
