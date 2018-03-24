package com.nebula.benchmark.types.number;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.number;


public class NumberRangeTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return number().range();
	}
}
