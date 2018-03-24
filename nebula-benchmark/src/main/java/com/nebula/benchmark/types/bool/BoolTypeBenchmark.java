package com.nebula.benchmark.types.bool;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.bool;

public class BoolTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return bool();
	}
}
