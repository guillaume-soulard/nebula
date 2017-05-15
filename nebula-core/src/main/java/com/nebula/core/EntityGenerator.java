package com.nebula.core;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;

public class EntityGenerator {

	public GeneratedObject generateEntityObject(Model model, Entity entity, long entityIndex, long seed) {
		NebulaRandom nebulaRandom = new NebulaRandom(seed + entityIndex);
		entity.init(new GenerationContext(nebulaRandom, model));
		GeneratedObject generateObject = entity.generateObject(nebulaRandom.nextIndex(entity));
		generateObject.getGeneratedProperties().add(new GeneratedProperty("_id", new GeneratedObject(entityIndex)));
		return generateObject;
	}
}
