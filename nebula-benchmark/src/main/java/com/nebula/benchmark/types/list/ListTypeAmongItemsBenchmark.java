package com.nebula.benchmark.types.list;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.benchmark.types.AbstractTypeBenchmark;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.types.RandomTypeBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.types.NebulaTypes.constant;
import static com.nebula.core.types.NebulaTypes.list;
import static com.nebula.core.generators.NebulaGenerators.random;

public class ListTypeAmongItemsBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return list().of(random()).amongItems(constant("value"));
	}
}
