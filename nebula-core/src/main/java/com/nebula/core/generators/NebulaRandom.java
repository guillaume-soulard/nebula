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

		long min = 0;
		long max = Integer.MAX_VALUE;
		long newMax = type.getMaxRange() - type.getMinRange();

		if (newMax >= 0) {
			max = newMax;
		}

		if (Integer.MAX_VALUE != max) {
			max++;
		}

		return random.longs(MAX_INT_TO_GENERATE, min, max).toArray()[0];
	}
}
