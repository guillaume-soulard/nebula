package com.nebula.benchmark.types.date;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;

import static com.nebula.core.types.NebulaTypes.dateTime;

public class DateRangeTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return dateTime().range();
	}
}
