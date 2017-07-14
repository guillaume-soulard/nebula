package com.nebula.core;

import com.nebula.core.types.Type;

public class GeneratedProperty {

	public static final String QUOTE = "\"";
	public static final String OBJECT_VALUE_SEPARATOR = ":";
	private String propertyName;
	private GeneratedObject propertyValue;
	private Type propertyType;

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
			if (other.propertyValue != null)
				return false;
		} else if (!propertyValue.equals(other.propertyValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(QUOTE).append(propertyName).append(QUOTE);
		builder.append(OBJECT_VALUE_SEPARATOR);
		builder.append(propertyValue.toString());
		return builder.toString();
	}
}
