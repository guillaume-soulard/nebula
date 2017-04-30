package com.nebula.core;

import java.util.List;

public class GeneratedObject {

	private List<GeneratedProperty> generatedProperties;
	private Object object;

	public GeneratedObject(Object object) {
		this.object = object;
	}

	public GeneratedObject(List<GeneratedProperty> generatedProperties) {
		this.generatedProperties = generatedProperties;
	}

	public Object getObject() {
		return object;
	}

	public List<GeneratedProperty> getGeneratedProperties() {
		return generatedProperties;
	}
}
