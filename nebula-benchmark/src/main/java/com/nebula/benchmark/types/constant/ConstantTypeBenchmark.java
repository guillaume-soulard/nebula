package com.nebula.benchmark.types.constant;

import com.nebula.benchmark.types.AbstractStaticTypeBenchmark;
import com.nebula.core.types.StaticTypeBuilder;

import static com.nebula.core.types.NebulaTypes.constant;

public class ConstantTypeBenchmark extends AbstractStaticTypeBenchmark {

	@Override
	protected StaticTypeBuilder getType() {
		return constant("test");
	}
}
