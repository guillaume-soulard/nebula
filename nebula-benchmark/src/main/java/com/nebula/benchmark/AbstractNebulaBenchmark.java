package com.nebula.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, iterations = 1)
@Measurement(time = 3, iterations = 3)
@Threads(1)
@Fork(1)
public abstract class AbstractNebulaBenchmark {
}
