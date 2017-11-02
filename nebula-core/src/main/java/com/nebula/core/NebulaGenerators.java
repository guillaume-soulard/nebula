package com.nebula.core;

import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.sequance.SequenceGeneratorBuilder;

public class NebulaGenerators {

	public static RandomGeneratorBuilder random() {
		return new RandomGeneratorBuilder();
	}

	public static SequenceGeneratorBuilder sequence() {
		return new SequenceGeneratorBuilder();
	}
}
