package com.nebula.benchmark.types.text;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.text;

public class TextTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return text();
	}
}
