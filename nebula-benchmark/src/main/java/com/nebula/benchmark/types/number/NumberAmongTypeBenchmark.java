package com.nebula.benchmark.types.number;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import java.math.BigDecimal;

import static com.nebula.core.types.NebulaTypes.number;

public class NumberAmongTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return number().among().items(BigDecimal.ZERO,
				BigDecimal.ONE, BigDecimal.TEN);
	}
}
