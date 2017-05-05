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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((generatedProperties == null) ? 0 : generatedProperties.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeneratedObject other = (GeneratedObject) obj;
		if (generatedProperties == null) {
			if (other.generatedProperties != null)
				return false;
		} else if (!generatedProperties.equals(other.generatedProperties))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (object != null) {
			builder.append(object.toString());
		} else if (generatedProperties != null) {
			builder.append(generatedProperties.toString());
		}
		return builder.toString();
	}
}
