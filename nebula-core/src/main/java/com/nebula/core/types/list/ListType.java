package com.nebula.core.types.list;

import java.util.ArrayList;
import java.util.List;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class ListType implements Type {

	private Type type;
	private Generator generator;
	private int maxSize;
	private int minSize;
	private NebulaRandom nebulaRandom;

	public ListType(int minSize, int maxSize, Generator generator, Type type) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.generator = generator;
		this.type = type;
	}

	@Override
	public void init(NebulaRandom nebulaRandom) {
		this.nebulaRandom = nebulaRandom;
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		NebulaRandom localNebulaRandom = new NebulaRandom(nebulaRandom.getSeed() + objectIndex);
		generator.init(localNebulaRandom);
		long listSize = localNebulaRandom.randomBetween(minSize, maxSize);
		List<Object> list = new ArrayList<>(Integer.valueOf(Long.toString(listSize)));
		for (int elementIndex = 0; elementIndex < listSize; elementIndex++) {
			list.add(generator.generate(type));
		}
		return new GeneratedObject(list);
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		return (long) (maxSize - minSize);
	}

}
