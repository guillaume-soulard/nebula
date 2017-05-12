package com.nebula.core.generators;

import java.util.Random;

import com.nebula.core.types.Type;

public class NebulaRandom {

	private static final int MAX_INT_TO_GENERATE = 1;
	private Random random;

	public NebulaRandom(long seed) {
		random = new Random(seed);
	}

	public Long nextIndex(Type type) {

		long min = type.getMinRange();
		long max = type.getMaxRange();
		return randomBetween(min, max);
	}

	public Long randomBetween(long min, long max) {
		if (Long.MAX_VALUE != max) {
			max++;
		}
		return random.longs(MAX_INT_TO_GENERATE, min, max).toArray()[0];
	}

	public Random getRandom() {
		return random;
	}
}
