package com.nebula.benchmark.types;

import com.nebula.core.Entity;
import com.nebula.core.types.StaticTypeBuilder;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, iterations = 1)
@Measurement(time = 3, iterations = 3)
@Threads(1)
@Fork(1)
public abstract class AbstractStaticTypeBenchmark extends AbstractTypeBenchmark {

    @Override
    protected void addProperty(Entity entity) {
        entity.addProperty("property", getType());
    }

    protected abstract StaticTypeBuilder getType();
}
