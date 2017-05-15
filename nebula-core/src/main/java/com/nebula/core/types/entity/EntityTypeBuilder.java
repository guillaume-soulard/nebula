package com.nebula.core.types.entity;

import com.nebula.core.NebulaException;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class EntityTypeBuilder implements TypeBuilder {

	private String entityName;

	@Override
	public Type build() {
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
