package com.nebula.core.types;

import com.nebula.core.Model;
import com.nebula.core.generators.NebulaRandom;

public class GenerationContext {

	private final NebulaRandom nebulaRandom;
	private final Model model;
	private final long entityIndex;

	public GenerationContext(NebulaRandom nebulaRandom, Model model, long entityIndex) {
		this.nebulaRandom = nebulaRandom;
		this.model = model;
		this.entityIndex = entityIndex;
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
}
