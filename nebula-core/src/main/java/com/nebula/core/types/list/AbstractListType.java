package com.nebula.core.types.list;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperties;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;

import java.util.List;

abstract class AbstractListType implements Type {

	final Generator generator;
	final int maxSize;
	final int minSize;
	GenerationContext context;

	AbstractListType(int minSize, int maxSize, Generator generator) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.generator = generator;
	}

	@Override
    public GeneratedObject generateObject(GeneratedProperties generatedProperties, Long objectIndex) {
		NebulaRandom localNebulaRandom = new NebulaRandom(context.getNebulaRandom().getSeed() + objectIndex);
		GenerationContext localContext = new GenerationContext(localNebulaRandom, context.getModel(), objectIndex, context.getDepth() + 1, context.getMaxDepth());
		generator.init(localContext);
		int listSize = Integer.valueOf(Long.toString(localNebulaRandom.randomBetween(minSize, maxSize)));
        return new GeneratedObject(generateList(generatedProperties, listSize, localNebulaRandom));
    }

    protected abstract List<Object> generateList(GeneratedProperties generatedProperties, int listSize, NebulaRandom nebulaRandom);

    void fillList(GeneratedProperties generatedProperties, int listSize, NebulaRandom nebulaRandom, List<Object> list) {
		for (int elementIndex = 0; elementIndex < listSize; elementIndex++) {
			NebulaRandom nebulaRandomForItem = new NebulaRandom(nebulaRandom.getSeed() + elementIndex);
			GenerationContext generationContextForItem = new GenerationContext(nebulaRandomForItem, context.getModel(), elementIndex, context.getDepth() + 1, context.getMaxDepth());
			generator.init(generationContextForItem);
            list.add(getItem(generatedProperties, nebulaRandomForItem));
		}
	}

    protected abstract Object getItem(GeneratedProperties generatedProperties, NebulaRandom nebulaRandom);

	@Override
	public void init(GenerationContext context) {
		this.context = context;
	}
}
