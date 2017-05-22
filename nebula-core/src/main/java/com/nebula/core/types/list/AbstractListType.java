package com.nebula.core.types.list;

import java.util.List;

import com.nebula.core.GeneratedObject;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

public abstract class AbstractListType implements Type {

	protected Generator generator;
	protected int maxSize;
	protected int minSize;
	protected GenerationContext context;

	public AbstractListType(int minSize, int maxSize, Generator generator) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.generator = generator;
	}

	@Override
	public GeneratedObject generateObject(Long objectIndex) {
		NebulaRandom localNebulaRandom = new NebulaRandom(context.getNebulaRandom().getSeed() + objectIndex);
		GenerationContext localContext = new GenerationContext(localNebulaRandom, context.getModel());
		generator.init(localContext);
		int listSize = Integer.valueOf(Long.toString(localNebulaRandom.randomBetween(minSize, maxSize)));
		return new GeneratedObject(generateList(listSize, localNebulaRandom));
	}

	protected abstract List<Object> generateList(int listSize, NebulaRandom nebulaRandom);

	protected void fillList(int listSize, NebulaRandom nebulaRandom, List<Object> list) {
		for (int elementIndex = 0; elementIndex < listSize; elementIndex++) {
			list.add(getItem(nebulaRandom));
		}
	}

	protected abstract Object getItem(NebulaRandom nebulaRandom);

	@Override
	public void init(GenerationContext context) {
		this.context = context;
	}
}
