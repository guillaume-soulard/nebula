package com.nebula.core;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;

import java.util.ArrayList;

public class EntityGenerator {

	public GeneratedObject generateEntityObject(Model model, Entity entity, long entityIndex, long seed, int depth, int maxDepth) {

		if (depth <= maxDepth) {

			NebulaRandom nebulaRandom = new NebulaRandom(seed + entityIndex);
			entity.init(new GenerationContext(nebulaRandom, model, entityIndex, depth, maxDepth));
            GeneratedObject generateObject = entity.generateObject(new ArrayList<>(), nebulaRandom.nextIndex(entity));
			generateObject.getGeneratedProperties().add(new GeneratedProperty("_id", new GeneratedObject(entityIndex), null));
			return generateObject;
		}

		return null;
	}
}
