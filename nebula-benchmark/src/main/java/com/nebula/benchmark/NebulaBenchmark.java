package com.nebula.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.nebula.benchmark.types.ListAmongItemsBenchmark;
import com.nebula.benchmark.types.ListOfTypeBenchmark;

public class NebulaBenchmark {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder().include(benchmarkClasses()).shouldDoGC(true).shouldFailOnError(true)
				.jvmArgs("-Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n").build();
		new Runner(options).run();
	}

	private static String benchmarkClasses() {
		StringBuilder builder = new StringBuilder();
		builder.append(OnePropertyOnEntityBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(TwoPropertyOnEntityBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(ThreePropertyOnEntityBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(FourPropertyOnEntityBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(FivePropertyOnEntityBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(ListOfTypeBenchmark.class.getSimpleName());
		builder.append("|");
		builder.append(ListAmongItemsBenchmark.class.getSimpleName());
		return builder.toString();
	}
}
