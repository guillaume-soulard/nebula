package com.nebula.benchmark;

import com.nebula.benchmark.types.bool.BoolTypeBenchmark;
import com.nebula.benchmark.types.constant.ConstantTypeBenchmark;
import com.nebula.benchmark.types.date.DateAmongTypeBenchmark;
import com.nebula.benchmark.types.date.DateRangeTypeBenchmark;
import com.nebula.benchmark.types.list.ListTypeAmongItemsBenchmark;
import com.nebula.benchmark.types.list.ListTypeOfTypeBenchmark;
import com.nebula.benchmark.types.number.NumberAmongTypeBenchmark;
import com.nebula.benchmark.types.number.NumberRangeTypeBenchmark;
import com.nebula.benchmark.types.string.StringTypeBenchmark;
import com.nebula.benchmark.types.text.TextTypeBenchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class NebulaBenchmark {

	private final List<Class<?>> benchmarkClasses;

	private NebulaBenchmark() {
		benchmarkClasses = new ArrayList<>();
		benchmarkClasses.add(ListTypeOfTypeBenchmark.class);
		benchmarkClasses.add(ListTypeAmongItemsBenchmark.class);
		benchmarkClasses.add(BoolTypeBenchmark.class);
		benchmarkClasses.add(ConstantTypeBenchmark.class);
		benchmarkClasses.add(NumberRangeTypeBenchmark.class);
		benchmarkClasses.add(NumberAmongTypeBenchmark.class);
		benchmarkClasses.add(DateRangeTypeBenchmark.class);
		benchmarkClasses.add(DateAmongTypeBenchmark.class);
		benchmarkClasses.add(StringTypeBenchmark.class);
		benchmarkClasses.add(TextTypeBenchmark.class);
	}

	public static void main(String[] args) {
		new NebulaBenchmark().runBenchmark();
	}

	private void runBenchmark() {
		try {
			Options options = new OptionsBuilder()
					.output("output.txt")
                    .resultFormat(ResultFormatType.SCSV)
					.result("result.txt")
					.include(getBenchmarkClassesRegexp()).shouldDoGC(true).shouldFailOnError(true)
					.jvmArgs("-Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n").build();
			new Runner(options).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getBenchmarkClassesRegexp() {
		if (benchmarkClasses.size() == 1) {
			return benchmarkClasses.get(0).getSimpleName();
		} else {
			return benchmarkClasses.stream().map(Class::getSimpleName).distinct().collect(Collectors.toList())
					.stream().reduce("", (a, b) -> a + "|" + b);
		}

	}
}
