package com.nebula.core;

public final class Nebula {

	public static Model newModel() {

		return new Model();
	}

	public static Entity newEntity(String entityName, int amount) throws NebulaException {

		if (entityName == null) {
			throw new NebulaException("entity name is null");
		}
		return new Entity(entityName, amount, new PropertyBuilder());
	}
}
