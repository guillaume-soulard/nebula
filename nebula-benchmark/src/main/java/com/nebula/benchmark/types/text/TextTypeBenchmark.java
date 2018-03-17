package com.nebula.benchmark.types.text;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.benchmark.types.AbstractTypeBenchmark;
import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.types.RandomTypeBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.string;
import static com.nebula.core.types.NebulaTypes.text;

public class TextTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return text();
	}
}
