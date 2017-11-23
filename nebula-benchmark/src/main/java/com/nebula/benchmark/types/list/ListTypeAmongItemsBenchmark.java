package com.nebula.benchmark.types.list;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.Entity;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.NebulaGenerationTypes.constant;
import static com.nebula.core.NebulaGenerationTypes.list;
import static com.nebula.core.NebulaGenerators.random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
@Measurement(time = 3, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Threads(1)
@Fork(1)
public class ListTypeAmongItemsBenchmark {

	private AtomicLong index;
	private Model model;
	private Entity entity;

	@Benchmark
	public void benchmark_list_of_number_with_size_1(Blackhole blackhole) {
		blackhole.consume(model.generateEntityObject(entity, index.getAndIncrement(), 0l));
	}

	@Setup(Level.Iteration)
	public void setup() {
		model = new ModelBuilder().build();
		entity = model.newEntity("test", 10000000);
		entity.addProperty("property", random(), list()
				.of(random()).amongItems(constant("value")));
		model.addEntity(entity);
		index = new AtomicLong(0l);
	}
}
