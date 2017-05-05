package com.nebula.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nebula.core.generators.NebulaRandom;

public class Model {

	private List<Entity> entities = new ArrayList<Entity>();

	public void addEntity(Entity entity) throws NebulaException {

		if (entity == null) {
			throw new NebulaException("type is null");
		}
		entities.add(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public Map<Entity, List<GeneratedObject>> generate(long seed) throws NebulaException {

		NebulaRandom nebulaRandom = new NebulaRandom(seed);
		Map<Entity, List<GeneratedObject>> result = new HashMap<Entity, List<GeneratedObject>>();

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
