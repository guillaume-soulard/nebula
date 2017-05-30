package com.nebula.core.types.number;

import org.junit.Test;

import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.types.Type;

public class NumberRangeTypeIT {

	@Test(timeout = 1000)
	public void generateObject_should_generate_at_least_10000000_object_over_1_second() {

		// GIVEN
		Type type = NebulaGenerationTypes.number().range().build();

		// WHEN
		for (int i = 1; i <= 9000000; i++) {
			type.generateObject(0l);
		}

		// THEN
	}
}
