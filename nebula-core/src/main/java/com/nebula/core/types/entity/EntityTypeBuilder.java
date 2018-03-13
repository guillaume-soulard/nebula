package com.nebula.core.types.entity;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.types.RandomTypeBuilder;
import com.nebula.core.types.Type;

public class EntityTypeBuilder implements RandomTypeBuilder {

	private String entityName;

	@Override
	public Type build(Model model) {
		return new EntityType(entityName);
	}

	public EntityTypeBuilder withName(String value) {
		if (value == null) {
			throw new NebulaException("entityName is null");
		}
		this.entityName = value;
		return this;
	}
}
