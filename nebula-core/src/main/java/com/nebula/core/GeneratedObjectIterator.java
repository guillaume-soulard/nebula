package com.nebula.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneratedObjectIterator implements Iterator<GeneratedObject> {

	private final Model model;
	private final Entity entity;
	private long nextIndexToGenerate = 0L;
	private final long seed;

	public GeneratedObjectIterator(Model model, Entity entity, long seed) {
		this.model = model;
		this.entity = entity;
		this.seed = seed;
	}

	@Override
	public boolean hasNext() {
		return nextIndexToGenerate < entity.getAmount();
	}

	@Override
	public GeneratedObject next() {

		if (!hasNext()) {
			throw new NoSuchElementException("Iteration has no more elements");
		}

		GeneratedObject generatedObject = model.generateEntityObject(entity, nextIndexToGenerate, seed);
		nextIndexToGenerate++;

		return generatedObject;
	}

	public Entity getEntity() {
		return entity;
	}
}
