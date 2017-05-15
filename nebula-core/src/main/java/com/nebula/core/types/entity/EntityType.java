package com.nebula.core.types.entity;

import com.nebula.core.Entity;
import com.nebula.core.EntityGenerator;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.core.types.AbstractTypeWithIndexCheck;

public class EntityType extends AbstractTypeWithIndexCheck {

	private Entity entity;
	private String entityName;
	private EntityGenerator entityGenerator = new EntityGenerator();

	public EntityType(String entityName) {
		if (entityName == null) {
			throw new NebulaException("entityName is null");
		}
		this.entityName = entityName;
	}

	@Override
	public Long getMinRange() {
		return 0l;
	}

	@Override
	public Long getMaxRange() {
		getEntityFromModelIfNeededElseThrowException();
		return entity.getAmount() - 1;
	}

	private void getEntityFromModelIfNeededElseThrowException() {
		entity = context.getModel().getEntityByName(entityName);
		if (entity == null) {
			throw new NebulaException("entity '" + entityName + "' is not defined in model");
		}
	}

	@Override
	protected GeneratedObject generatedObjectAtIndex(Long index) {
		getEntityFromModelIfNeededElseThrowException();
		return entityGenerator.generateEntityObject(context.getModel(), entity, index,
				context.getNebulaRandom().getSeed());
	}
}