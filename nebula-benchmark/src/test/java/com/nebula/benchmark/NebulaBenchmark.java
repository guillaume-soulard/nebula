package com.nebula.benchmark;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.Nebula;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 2)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 10)
@Threads(1)
@Fork(1)
public class NebulaBenchmark {

	private Model model;
	private Entity entityWithOnePropertyBool;
	private Entity entityWithOnePropertyString;
	private Entity entityWithOnePropertyNumber;
	private Entity entityWithOnePropertyConstant;
	private Entity entityWithOnePropertyDateTime;
	private Entity entityWithOnePropertyList;
	private Entity entityWithOnePropertyEntity;

	@Test
	public void launchBenchmark() throws RunnerException {

		// GIVEN
		Options opt = new OptionsBuilder().include(NebulaBenchmark.class.getSimpleName()).shouldFailOnError(true)
				.shouldDoGC(true).build();

		// WHEN
		new Runner(opt).run();

		// THEN
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_bool(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyBool, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_string(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyString, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_number(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyNumber, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_contant(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyConstant, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_dateTime(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyDateTime, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_list(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyList, 0l, 0l));
	}

	@Benchmark
	public void benchmark_generateObject_from_entity_with_one_property_entity(Blackhole bh) {
		bh.consume(model.generateEntityObject(entityWithOnePropertyEntity, 0l, 0l));
	}

	@Setup(Level.Iteration)
	public void setup() {
		setupModel();
		setupEntityWithOnePropertyBool();
		setupEntityWithOnePropertyString();
		setupEntityWithOnePropertyNumber();
		setupEntityWithOnePropertyConstant();
		setupEntityWithOnePropertyDateTime();
		setupEntityWithOnePropertyList();
		setupEntityWithOnePropertyEntity();
	}

	private void setupEntityWithOnePropertyEntity() {
		entityWithOnePropertyEntity = Nebula.newEntity("entityWithOnePropertyEntity", 1000000);
		entityWithOnePropertyEntity.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.entity("entityWithOnePropertyBool"));
		model.addEntity(entityWithOnePropertyEntity);
	}

	private void setupEntityWithOnePropertyList() {
		entityWithOnePropertyList = Nebula.newEntity("entityWithOnrPropertyList", 1000000);
		entityWithOnePropertyList.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.list().of(NebulaGenerators.random(), NebulaGenerationTypes.bool()));
		model.addEntity(entityWithOnePropertyList);
	}

	private void setupEntityWithOnePropertyDateTime() {
		entityWithOnePropertyDateTime = Nebula.newEntity("entityWithOnePropertyDateTime", 1000000);
		entityWithOnePropertyDateTime.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.dateTime().range());
		model.addEntity(entityWithOnePropertyDateTime);
	}

	private void setupEntityWithOnePropertyConstant() {
		entityWithOnePropertyConstant = Nebula.newEntity("entityWithOnePropertyConstant", 1000000);
		entityWithOnePropertyConstant.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.constant("constant"));
		model.addEntity(entityWithOnePropertyConstant);
	}

	private void setupEntityWithOnePropertyNumber() {
		entityWithOnePropertyNumber = Nebula.newEntity("entityWithOnePropertyNumber", 1000000);
		entityWithOnePropertyNumber.addProperty("proeprty", NebulaGenerators.random(),
				NebulaGenerationTypes.number().range());
		model.addEntity(entityWithOnePropertyNumber);
	}

	private void setupEntityWithOnePropertyString() {
		entityWithOnePropertyString = Nebula.newEntity("entityWithOnePropertyString", 1000000);
		entityWithOnePropertyString.addProperty("property", NebulaGenerators.random(),
				NebulaGenerationTypes.string().withPattern("[A-Z]{1}[a-z]{10,25}"));
		model.addEntity(entityWithOnePropertyString);
	}

	private void setupModel() {
		model = Nebula.newModel();
	}

	private void setupEntityWithOnePropertyBool() {
		entityWithOnePropertyBool = Nebula.newEntity("entityWithOnePropertyBool", 1000000);
		entityWithOnePropertyBool.addProperty("property", NebulaGenerators.random(), NebulaGenerationTypes.bool());
		model.addEntity(entityWithOnePropertyBool);
	}
}
