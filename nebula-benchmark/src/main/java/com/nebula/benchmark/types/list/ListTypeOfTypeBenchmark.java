package com.nebula.benchmark.types.list;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.list;
import static com.nebula.core.types.NebulaTypes.number;
import static com.nebula.core.generators.NebulaGenerators.random;

public class ListTypeOfTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return list().of(random(), number().range());
	}
}
