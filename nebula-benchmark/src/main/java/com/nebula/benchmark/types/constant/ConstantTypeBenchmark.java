package com.nebula.benchmark.types.constant;

import com.nebula.benchmark.types.AbstractStaticTypeBenchmark;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.types.StaticTypeBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.nebula.core.types.NebulaTypes.constant;

public class ConstantTypeBenchmark extends AbstractStaticTypeBenchmark {

	@Override
	protected StaticTypeBuilder getType() {
		return constant("test");
	}
}
