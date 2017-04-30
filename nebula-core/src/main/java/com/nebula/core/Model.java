package com.nebula.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;

public class Model {

	private List<Entity> entities = new ArrayList<Entity>();

	public void addEntity(Entity type) throws NebulaException {

		if (type == null) {
			throw new NebulaException("type is null");
		}
		entities.add(type);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public Map<Type, List<GeneratedObject>> generate(long seed) throws NebulaException {

		NebulaRandom nebulaRandom = new NebulaRandom(seed);
		Map<Type, List<GeneratedObject>> result = new HashMap<Type, List<GeneratedObject>>();

		for (Entity entity : entities) {
			entity.init(nebulaRandom);
			List<GeneratedObject> generatedObjects = new LinkedList<GeneratedObject>();
			for (int i = 1; i <= entity.getAmount(); i++) {
				generatedObjects.add(entity.generateObject(nebulaRandom.nextIndex(entity)));
			}

			result.put(entity, generatedObjects);
		}

		return result;
	}
}
