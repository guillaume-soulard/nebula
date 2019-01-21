package com.nebula.core.types;

import com.nebula.core.Model;
import com.nebula.core.generators.NebulaRandom;

public class GenerationContext {

	private final NebulaRandom nebulaRandom;
	private final Model model;
	private final long entityIndex;
	private final int depth;
	private final int maxDepth;

	private GenerationContext(NebulaRandom nebulaRandom, Model model, long entityIndex, int depth, int maxDepth) {
		this.nebulaRandom = nebulaRandom;
		this.model = model;
		this.entityIndex = entityIndex;
		this.depth = depth;
		this.maxDepth = maxDepth;
	}

	public static GenerationContext of(NebulaRandom nebulaRandom, Model model, long entityIndex, int depth, int maxDepth) {
		return new GenerationContext(nebulaRandom, model, entityIndex, depth, maxDepth);
	}

	public NebulaRandom getNebulaRandom() {
		return nebulaRandom;
	}

	public Model getModel() {
		return model;
	}

	public long getEntityIndex() {
		return entityIndex;
	}

	public int getDepth() {
		return depth;
	}

	public int getMaxDepth() {
		return maxDepth;
	}
}
