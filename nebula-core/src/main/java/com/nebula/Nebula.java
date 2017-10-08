package com.nebula;

import com.nebula.core.Entity;
import com.nebula.core.NebulaException;
import com.nebula.core.PropertyBuilder;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;

public final class Nebula {

	public static Model newModel() {

		return new Model();
	}

	public static Entity newEntity(String entityName, long amount) {

		if (entityName == null) {
			throw new NebulaException("entity name is null");
		}
		return new Entity(entityName, amount, new PropertyBuilder());
	}

	public static GenerationRuleBuilder newGenerationRule() {
		return new GenerationRuleBuilder();
	}
}
