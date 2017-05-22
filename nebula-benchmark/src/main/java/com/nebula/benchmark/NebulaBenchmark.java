package com.nebula.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.nebula.benchmark.types.ListAmongItemsBenchmark;
import com.nebula.benchmark.types.ListOfTypeBenchmark;

public class NebulaBenchmark {

	private List<Class<?>> benchmarkClasses;

	private NebulaBenchmark() {
		benchmarkClasses = new ArrayList<Class<?>>();
		benchmarkClasses.add(OnePropertyOnEntityBenchmark.class);
		benchmarkClasses.add(TwoPropertyOnEntityBenchmark.class);
		benchmarkClasses.add(ThreePropertyOnEntityBenchmark.class);
		benchmarkClasses.add(FourPropertyOnEntityBenchmark.class);
		benchmarkClasses.add(FivePropertyOnEntityBenchmark.class);
		benchmarkClasses.add(ListOfTypeBenchmark.class);
		benchmarkClasses.add(ListAmongItemsBenchmark.class);
	}

	public static void main(String[] args) throws RunnerException {
		new NebulaBenchmark().runBenchmark();
	}

	private void runBenchmark() throws RunnerException {
		Options options = new OptionsBuilder().output(getOuputFileName()).result(getResultFileName())
				.include(getBenchmarkClassesRegexp()).shouldDoGC(true).shouldFailOnError(true)
				.jvmArgs("-Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n").build();
		new Runner(options).run();
	}

	private String getResultFileName() {
		return "result.txt";
	}

	private String getOuputFileName() {
		return "logs.txt";
	}

	private String getBenchmarkClassesRegexp() {
		return benchmarkClasses.stream().map(clazz -> clazz.getSimpleName()).distinct().collect(Collectors.toList())
				.stream().reduce(getOuputFileName(), (a, b) -> a + "|" + b);

	}
}
