package com.nebula.core;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.RandomGenerator;

public class NebulaGenerators {

	public static Generator random() {
		return new RandomGenerator();
	}
}
