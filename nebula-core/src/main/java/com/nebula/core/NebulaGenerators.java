package com.nebula.core;

import com.nebula.core.generators.RandomGenerator;
import com.nebula.core.generators.SequanceGenerator;

public class NebulaGenerators {

	public static RandomGenerator random() {
		return new RandomGenerator();
	}

	public static SequanceGenerator sequance() {
		return new SequanceGenerator();
	}
}
