package com.nebula.core;

public final class NebulaCore {

	public static Model newModel() {

		return new Model();
	}

	public static Entity newEntity(String entityName, long amount) {

		if (entityName == null) {
			throw new NebulaException("entity name is null");
		}
		return new Entity(entityName, amount, new PropertyBuilder());
	}
}
