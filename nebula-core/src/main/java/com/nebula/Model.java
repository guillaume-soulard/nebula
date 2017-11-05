package com.nebula;

import com.nebula.core.*;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;
import com.nebula.generationrule.oneshoot.OneShootGenerationRule;
import com.nebula.generationrule.oneshoot.OneShootGenerationRuleBuilder;

import java.util.*;

public class Model {

	private long seed;
	private List<Entity> entities = new ArrayList<Entity>();
	private EntityGenerator entityGenerator = new EntityGenerator();
	private List<GenerationRule> generationRules = new ArrayList<>();
	private String dateFormat = "MM/dd/yyyy";
	private char numberDecimalSeparator = '.';
	private char numberThousandSeparator = ',';

	public Model() {
		seed = new Random().nextLong();
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

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public void generate() {
		for (GenerationRule generationRule : generationRules) {
			generationRule.generate();
		}
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public char getNumberDecimalSeparator() {
		return numberDecimalSeparator;
	}

	public void setNumberDecimalSeparator(char numberDecimalSeparator) {
		this.numberDecimalSeparator = numberDecimalSeparator;
	}

	public char getNumberThousandSeparator() {
		return numberThousandSeparator;
	}

	public void setNumberThousandSeparator(char numberThousandSeparator) {
		this.numberThousandSeparator = numberThousandSeparator;
	}

	public void setSeed(String seedString) {
		if (seedString == null) {
			throw new NebulaException("seed is null");
		}

		long longSeed = 0l;

		for (byte seedByte : seedString.getBytes()) {
			longSeed += 31 * seedByte;
		}

		this.seed = longSeed;
	}
}
