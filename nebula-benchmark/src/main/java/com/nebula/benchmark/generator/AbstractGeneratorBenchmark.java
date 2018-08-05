package com.nebula.benchmark.generator;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.types.NebulaTypes;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generationconstraint.NebulaConstraints.amount;
import static com.nebula.core.generationrule.GenerationRules.newOneShootGenerationRule;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static com.nebula.core.types.NebulaTypes.bool;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, iterations = 1)
@Measurement(time = 3, iterations = 3)
@Threads(1)
@Fork(1)
abstract class AbstractGeneratorBenchmark {

    private Model model;

    @Setup(Level.Iteration)
    public void setup() {
        model = ModelBuilder.newEmptyModel()
                .build();
        model.newEntity("test")
            .addProperty("property", getGenerator(), bool());

        model.addGenerationRule(newOneShootGenerationRule()
                .withEntity("test")
                .addOutput(stdout())
                .withFormatter(json())
                .addGenerationConstraint(amount(10000)));
    }

    @Benchmark
    public void benchmarkType() {
        model.generate();
    }

    protected abstract GeneratorBuilder getGenerator();
}
