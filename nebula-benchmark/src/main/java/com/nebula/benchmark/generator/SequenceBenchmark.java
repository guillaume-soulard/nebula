package com.nebula.benchmark.generator;

import com.nebula.core.generators.GeneratorBuilder;

import static com.nebula.core.generators.NebulaGenerators.sequence;

public class SequenceBenchmark extends AbstractGeneratorBenchmark {

    @Override
    protected GeneratorBuilder getGenerator() {
        return sequence();
    }
}
