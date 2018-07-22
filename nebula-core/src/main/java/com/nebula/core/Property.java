package com.nebula.core;

import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;

public class Property {

	private final String name;

	private final Type type;

	private final Generator generator;

	public Property(String name, Type type, Generator generator) {

		if (name.contains(".")) {
			throw new NebulaException("Property name must not contains '.'");
		}

		this.name = name;
		this.type = type;
		this.generator = generator;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public Generator getGenerator() {
		return generator;
	}
}
