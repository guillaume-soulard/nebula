package com.nebula.core;

import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;

public class Property {

	private String name;

	private Type type;

	private Generator generator;

	public Property(String name, Type type, Generator generator) {
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
