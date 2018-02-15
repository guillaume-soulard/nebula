package com.nebula.core;

import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;

import java.util.*;

public class Model {

	private final long seed;
	private final String dateFormat;
	private final Character numberDecimalSeparator;
	private final Character numberThousandSeparator;
	private List<Entity> entities = new ArrayList<>();
	private EntityGenerator entityGenerator = new EntityGenerator();
	private List<GenerationRule> generationRules = new ArrayList<>();

	Model(long seed, Character numberThousandSeparator, Character numberDecimalSeparator, String dateFormat) {
		this.seed = seed;
		this.numberThousandSeparator = numberThousandSeparator;
		this.numberDecimalSeparator = numberDecimalSeparator;
		this.dateFormat = dateFormat;
	}

	public Entity newEntity(String entityName, long amount) {

		if (entityName == null) {
			throw new NebulaException("entity name is null");
		}
		return new Entity(this, entityName, amount, new PropertyBuilder());
	}

	public Entity newEntity(String entityName) {
		return newEntity(entityName, Long.MAX_VALUE);
	}

	public void addEntity(Entity entity) {

		if (entity == null) {
			throw new NebulaException("type is null");
		}
		entities.add(entity);
	}

	public void addGenerationRule(GenerationRuleBuilder generationRuleBuilder) {
		this.generationRules.add(generationRuleBuilder.build(this));
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public Map<Entity, List<GeneratedObject>> generateEntitiesObjectsAll(long seed) {
		Map<Entity, List<GeneratedObject>> result = new HashMap<>();
		for (Entity entity : entities) {
			result.put(entity, generateEntityObjects(entity, seed));
		}
		return result;
	}

	public List<GeneratedObject> generateEntityObjects(Entity entity, long seed) {
		List<GeneratedObject> generatedObjects = new LinkedList<>();

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
		throw new NebulaException("entity '" + entityName + "' not exists in model");
	}

	public GeneratedObjectIterator iterator(String entityName, long seed) {
		return new GeneratedObjectIterator(this, getEntityByName(entityName), seed);
	}

	public GeneratedObject generateEntityObject(Entity entity, long entityIndex, long seed) {
		return entityGenerator.generateEntityObject(this, entity, entityIndex, seed);
	}

	public GeneratedObject generateEntityObject(String entityName, long entityIndex) {
		return entityGenerator.generateEntityObject(this, getEntityByName(entityName), entityIndex, seed);
	}

	public long getSeed() {
		return seed;
	}

	public void generate() {
        generationRules.forEach(GenerationRule::generate);
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public Character getNumberDecimalSeparator() {
		return numberDecimalSeparator;
	}

	public Character getNumberThousandSeparator() {
		return numberThousandSeparator;
	}
}
