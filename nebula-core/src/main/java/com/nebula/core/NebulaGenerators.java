package com.nebula.core;

import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.sequance.SequanceGeneratorBuilder;

public class NebulaGenerators {

	public static RandomGeneratorBuilder random() {
		return new RandomGeneratorBuilder();
	}

	public static SequanceGeneratorBuilder sequance() {
		return new SequanceGeneratorBuilder();
	}
}
