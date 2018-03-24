package com.nebula.benchmark.types.list;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.constant;
import static com.nebula.core.types.NebulaTypes.list;
import static com.nebula.core.generators.NebulaGenerators.random;

public class ListTypeAmongItemsBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return list().of(random()).amongItems(constant("value"));
	}
}
