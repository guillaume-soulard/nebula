package com.nebula.benchmark.model;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationconstraint.NebulaConstraints;
import com.nebula.core.generationrule.GenerationRules;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.output.NebulaOutputs;
import com.nebula.core.types.NebulaTypes;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generationconstraint.NebulaConstraints.amount;
import static com.nebula.core.generationrule.GenerationRules.newOneShootGenerationRule;
import static com.nebula.core.output.NebulaOutputs.stdout;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, iterations = 1)
@Measurement(time = 3, iterations = 3)
@Threads(1)
@Fork(1)
abstract class AbstractEntityBenchmark {

    private Model model;

    @Setup(Level.Iteration)
    public void setup() {
        model = ModelBuilder.newEmptyModel()
                .build();
        setupBenchmark();
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

    protected abstract void setupBenchmark();

    void addNewEntityWithFields(int numberOfFields) {

        Entity entity = model.newEntity("test");

        IntStream.rangeClosed(1, numberOfFields).forEach(i -> {

            if (i % 1 == 0) {

                entity.addProperty(UUID.randomUUID().toString(), NebulaGenerators.random(), NebulaTypes.number().range());
            } else if (i % 2 == 0) {

                entity.addProperty(UUID.randomUUID().toString(), NebulaGenerators.random(), NebulaTypes.dateTime().range());
            } else if (i % 2 == 0) {

                entity.addProperty(UUID.randomUUID().toString(), NebulaGenerators.random(), NebulaTypes.bool());
            } else {

                entity.addProperty(UUID.randomUUID().toString(), NebulaGenerators.random(), NebulaTypes.text());
            }

        });
    }
}
