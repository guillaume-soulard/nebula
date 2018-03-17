package com.nebula.benchmark.types;

import com.nebula.core.Entity;
import com.nebula.core.ModelBuilder;
import com.nebula.core.types.RandomTypeBuilder;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.generators.NebulaGenerators.random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, iterations = 1)
@Measurement(time = 3, iterations = 3)
@Threads(1)
@Fork(1)
public abstract class AbstractRandomTypeBenchmark extends AbstractTypeBenchmark {

    @Override
    protected void addProperty(Entity entity) {
        entity.addProperty("property", random(), getType());
    }

    protected abstract RandomTypeBuilder getType();
}
