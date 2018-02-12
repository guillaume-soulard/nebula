package com.nebula.core.generators;

import com.nebula.core.generators.custom.CustomGeneratorBuilder;
import com.nebula.core.generators.random.RandomGeneratorBuilder;
import com.nebula.core.generators.randomunique.RandomUniqueGeneratorBuilder;
import com.nebula.core.generators.sequance.SequenceGeneratorBuilder;

public class NebulaGenerators {

	public static RandomGeneratorBuilder random() {
		return new RandomGeneratorBuilder();
	}

	public static SequenceGeneratorBuilder sequence() {
		return new SequenceGeneratorBuilder();
	}

	public static CustomGeneratorBuilder custom(Generator generator) { return new CustomGeneratorBuilder(generator);  }

	public static RandomUniqueGeneratorBuilder randomUnique() { return new RandomUniqueGeneratorBuilder();  }
}
