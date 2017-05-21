package com.nebula.benchmark;

import java.util.concurrent.TimeUnit;

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
import com.nebula.core.types.TypeBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 2)
@Measurement(time = 3, timeUnit = TimeUnit.SECONDS, iterations = 3)
@Threads(1)
@Fork(1)
public abstract class AbstractPropertyOnEntityBenchmark {

	private int numberOfProperties = 0;
	private Model model;
	private Entity entityWithPropertyBool;
	private Entity entityWithPropertyString;
	private Entity entityWithPropertyNumber;
	private Entity entityWithPropertyConstant;
	private Entity entityWithPropertyDateTime;
	private Entity entityWithPropertyList;
	private Entity entityWithPropertyEntity;

	public AbstractPropertyOnEntityBenchmark(int numberOfProperties) {
		this.numberOfProperties = numberOfProperties;
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_bool(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyBool);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_string(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyString);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_number(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyNumber);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_contant(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyConstant);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_dateTime(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyDateTime);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_list(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyList);
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_property_entity(Blackhole bh) {
		benchmarkEntity(bh, entityWithPropertyEntity);
	}

	private void benchmarkEntity(Blackhole bh, Entity entity) {
		bh.consume(model.generateEntityObject(entity, 0l, 0l));
	}

	@Setup(Level.Iteration)
	public void setup() {
		setupModel();
		setupEntityWithPropertyBool();
		setupEntityWithPropertyString();
		setupEntityWithPropertyNumber();
		setupEntityWithPropertyConstant();
		setupEntityWithPropertyDateTime();
		setupEntityWithPropertyList();
		setupEntityWithPropertyEntity();
	}

	private void setupEntityWithPropertyEntity() {
		entityWithPropertyEntity = Nebula.newEntity("entityWithPropertyEntity", 1000000);
		generateNProperties(entityWithPropertyEntity, NebulaGenerationTypes.entity("entityWithPropertyBool"));
	}

	private void setupEntityWithPropertyList() {
		entityWithPropertyList = Nebula.newEntity("entityWithPropertyList", 1000000);
		generateNProperties(entityWithPropertyList, NebulaGenerationTypes.bool());
	}

	private void setupEntityWithPropertyDateTime() {
		entityWithPropertyDateTime = Nebula.newEntity("entityWithPropertyDateTime", 1000000);
		generateNProperties(entityWithPropertyDateTime, NebulaGenerationTypes.dateTime().range());
	}

	private void setupEntityWithPropertyConstant() {
		entityWithPropertyConstant = Nebula.newEntity("entityWithPropertyConstant", 1000000);
		generateNProperties(entityWithPropertyConstant, NebulaGenerationTypes.constant("constant"));
	}

	private void setupEntityWithPropertyNumber() {
		entityWithPropertyNumber = Nebula.newEntity("entityWithPropertyNumber", 1000000);
		generateNProperties(entityWithPropertyNumber, NebulaGenerationTypes.number().range());
	}

	private void setupEntityWithPropertyString() {
		entityWithPropertyString = Nebula.newEntity("entityWithPropertyString", 1000000);
		generateNProperties(entityWithPropertyString,
				NebulaGenerationTypes.string().withPattern("[A-Z]{1}[a-z]{10,25}"));
	}

	private void setupEntityWithPropertyBool() {
		entityWithPropertyBool = Nebula.newEntity("entityWithPropertyBool", 1000000);
		generateNProperties(entityWithPropertyBool, NebulaGenerationTypes.bool());
	}

	private void setupModel() {
		model = Nebula.newModel();
	}

	private void generateNProperties(Entity entity, TypeBuilder typeBuilder) {
		for (int i = 1; i <= numberOfProperties; i++) {
			entity.addProperty("property" + i, NebulaGenerators.random(), typeBuilder);
		}
		model.addEntity(entity);
	}
}