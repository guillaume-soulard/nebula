package com.nebula.benchmark.types.list;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.Nebula;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;

import static com.nebula.core.Nebula.newEntity;
import static com.nebula.core.Nebula.newModel;
import static com.nebula.core.NebulaGenerationTypes.number;
import static com.nebula.core.NebulaGenerators.random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
@Measurement(time = 3, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Threads(1)
@Fork(1)
public class ListTypeOfTypeBenchmark {

	private AtomicLong index;
	private Model model;
	private Entity entity;

	@Benchmark
	public void benchmark_list_of_number_with_size_1(Blackhole blackhole) {
		blackhole.consume(model.generateEntityObject(entity, index.getAndIncrement(), 0l));
	}

	@Setup(Level.Iteration)
	public void setup() {
		model = newModel();
		entity = newEntity("test", 10000000);
		entity.addProperty("property", random(),
				NebulaGenerationTypes.list().of(random(), number().range()));
		model.addEntity(entity);
		index = new AtomicLong(0l);
	}
}
