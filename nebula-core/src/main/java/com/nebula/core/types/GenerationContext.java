package com.nebula.core.types;

import com.nebula.core.Model;
import com.nebula.core.generators.NebulaRandom;

public class GenerationContext {

	private NebulaRandom nebulaRandom;
	private Model model;

	public GenerationContext(NebulaRandom nebulaRandom, Model model) {
		this.nebulaRandom = nebulaRandom;
		this.model = model;
	}

	public NebulaRandom getNebulaRandom() {
		return nebulaRandom;
	}

	public Model getModel() {
		return model;
	}
}
