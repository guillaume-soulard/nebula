package com.nebula.benchmark.types.number;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.NebulaCore.newEntity;
import static com.nebula.core.NebulaCore.newModel;
import static com.nebula.core.NebulaGenerationTypes.constant;
import static com.nebula.core.NebulaGenerationTypes.number;
import static com.nebula.core.NebulaGenerators.random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
@Measurement(time = 3, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Threads(1)
@Fork(1)
public class NumberRangeTypeBenchmark {

	private AtomicLong index;
	private Model model;
	private Entity entity;

	@Benchmark
	public void benchmark_number_range_type(Blackhole blackhole) {
		blackhole.consume(model.generateEntityObject(entity, index.getAndIncrement(), 0l));
	}

	@Setup(Level.Iteration)
	public void setup() {
		model = newModel();
		entity = newEntity("test", 10000000);
		entity.addProperty("property", random(), number().range());
		model.addEntity(entity);
		index = new AtomicLong(0l);
	}
}
