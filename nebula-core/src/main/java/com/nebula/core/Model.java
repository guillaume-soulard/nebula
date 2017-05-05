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

	public Map<Entity, List<GeneratedObject>> generateAll(long seed) throws NebulaException {
		Map<Entity, List<GeneratedObject>> result = new HashMap<Entity, List<GeneratedObject>>();
		for (Entity entity : entities) {
			result.put(entity, generateEntity(entity, seed));
		}
		return result;
	}

	public List<GeneratedObject> generateEntity(Entity entity, long seed) throws NebulaException {
		List<GeneratedObject> generatedObjects = new LinkedList<GeneratedObject>();

		for (int index = 0; index < entity.getAmount(); index++) {
			generatedObjects.add(generateEntity(entity, index, seed));
		}

		return generatedObjects;
	}

	public Entity getEntityByName(String entityName) {
		for (Entity entity : entities) {
			if (entity.getName().equals(entityName)) {
				return entity;
			}
		}
		return null;
	}

	public GeneratedObjectIterator iterator(String entityName, long seed) {
		return new GeneratedObjectIterator(this, getEntityByName(entityName), seed);
	}

	public GeneratedObject generateEntity(Entity entity, long entityIndex, long seed) throws NebulaException {
		NebulaRandom nebulaRandom = new NebulaRandom(seed + entityIndex);
		entity.init(nebulaRandom);
		GeneratedObject generateObject = entity.generateObject(nebulaRandom.nextIndex(entity));
		generateObject.getGeneratedProperties().add(new GeneratedProperty("_id", new GeneratedObject(entityIndex)));
		return generateObject;
	}
}
