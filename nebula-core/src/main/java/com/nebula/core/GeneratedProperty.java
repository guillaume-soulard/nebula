package com.nebula.core;

import com.nebula.core.types.Type;

public class GeneratedProperty {

	private static final String QUOTE = "\"";
	private static final String OBJECT_VALUE_SEPARATOR = ":";
	private final String propertyName;
	private final GeneratedObject propertyValue;
	private final Type propertyType;

	public GeneratedProperty(String propertyName, GeneratedObject propertyValue, Type propertyType) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
		this.propertyType = propertyType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public GeneratedObject getPropertyValue() {
		return propertyValue;
	}

	public Type getPropertyType() {
		return propertyType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + ((propertyValue == null) ? 0 : propertyValue.hashCode());
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
		GeneratedProperty other = (GeneratedProperty) obj;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (propertyValue == null) {
			return other.propertyValue == null;
		} else {
			return propertyValue.equals(other.propertyValue);
		}
	}

	@Override
	public String toString() {
		String builder = QUOTE + propertyName + QUOTE +
				OBJECT_VALUE_SEPARATOR +
				propertyValue.toString();
		return builder;
	}
}
