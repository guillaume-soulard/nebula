package com.nebula.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Model {

	private List<Entity> entities = new ArrayList<Entity>();
	private EntityGenerator entityGenerator = new EntityGenerator();

	public void addEntity(Entity entity) {

		if (entity == null) {
			throw new NebulaException("type is null");
		}
		entities.add(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public Map<Entity, List<GeneratedObject>> generateEntitiesObjectsAll(long seed) {
		Map<Entity, List<GeneratedObject>> result = new HashMap<Entity, List<GeneratedObject>>();
		for (Entity entity : entities) {
			result.put(entity, generateEntityObjects(entity, seed));
		}
		return result;
	}

	public List<GeneratedObject> generateEntityObjects(Entity entity, long seed) {
		List<GeneratedObject> generatedObjects = new LinkedList<GeneratedObject>();

		for (int index = 0; index < entity.getAmount(); index++) {
			generatedObjects.add(generateEntityObject(entity, index, seed));
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

	public GeneratedObject generateEntityObject(Entity entity, long entityIndex, long seed) {
		return entityGenerator.generateEntityObject(this, entity, entityIndex, seed);
	}
}
