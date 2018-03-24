package com.nebula.benchmark.types.date;

import com.nebula.benchmark.types.AbstractRandomTypeBenchmark;
import com.nebula.core.types.RandomTypeBuilder;
import org.joda.time.DateTime;

import static com.nebula.core.types.NebulaTypes.dateTime;

public class DateAmongTypeBenchmark extends AbstractRandomTypeBenchmark {

	@Override
	protected RandomTypeBuilder getType() {
		return dateTime().among().items(
				new DateTime(2018, 1, 1, 0, 0),
				new DateTime(2018, 1, 2, 0, 0),
				new DateTime(2018, 1, 3, 0, 0));
	}
}
