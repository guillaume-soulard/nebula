package com.nebula.core;

import java.util.*;

public class GeneratedObject {

	private static final String QUOTE = "\"";
	private static final String OBJECT_SEPARATOR = ",";
	private static final String ARRAY_START = "[";
	private static final String ARRAY_END = "]";
	private static final String OBJECT_START = "{";
	private static final String OBJECT_END = "}";
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
			return other.object == null;
		} else {
			return object.equals(other.object);
		}
	}

	@Override
	public String toString() {
		if (object != null) {
			return getObjectString();
		} else {
			return getPropertiesString();
		}
	}

	private String getPropertiesString() {
		StringBuilder builder = new StringBuilder(OBJECT_START);
		for (GeneratedProperty generatedProperty : generatedProperties) {
            if (isFirstItemOfList(generatedProperties, generatedProperty)) {
                builder.append(OBJECT_SEPARATOR);
            }
            builder.append(generatedProperty.toString());
        }
		return builder.append(OBJECT_END).toString();
	}

	private String getObjectString() {
		if (isString(object)) {
			return getObjectAsString();
		}
		if (isList(object)) {
			return getListAsString();
        }
		return object.toString();
	}

	private String getListAsString() {
		List<?> list = (List<?>) object;
		StringBuilder builder = new StringBuilder(ARRAY_START);
		for (Object item : list) {
            if (isFirstItemOfList(list, item)) {
                builder.append(OBJECT_SEPARATOR);
            }

            if (item instanceof String) {
                builder.append(QUOTE).append(item.toString()).append(QUOTE);
            } else {
                builder.append(item.toString());
            }
        }
		return builder.append(ARRAY_END).toString();
	}

	private boolean isFirstItemOfList(List<?> list, Object item) {
		return list.indexOf(item) != 0;
	}

	private String getObjectAsString() {
		return QUOTE + object + QUOTE;
	}

	private boolean isList(Object objectToCheck) {
		return objectToCheck instanceof List;
	}

	private boolean isString(Object objectToCheck) {
		return objectToCheck instanceof String;
	}

	public GeneratedObject getGeneratedPropertyValue(String propertyName) {

		if (generatedProperties != null) {
			for (GeneratedProperty generatedProperty : generatedProperties) {
				if (generatedProperty.getPropertyName().equals(propertyName)) {
					return generatedProperty.getPropertyValue();
				}
			}
		}

		throw new NebulaException("Property '" + propertyName + "' is undefined");
	}

	public Object getValueByPath(String value) {

		Stack<String> paths = new Stack<>();
		List<String> pathList = Arrays.asList(value.split("\\."));
		Collections.reverse(pathList);
		paths.addAll(pathList);
		return recursiveGetValueByPath(paths);
	}

	private Object recursiveGetValueByPath(Stack<String> paths) {

		String path = paths.pop();

		if (isFinalObject()) {

			return object;
		} else {

			Optional<GeneratedProperty> property = generatedProperties
					.stream()
					.filter(p -> p.getPropertyName().equals(path))
					.findFirst();

			if (property.isPresent()) {

				if (paths.isEmpty()) {
					return property.get().getPropertyValue().getObject();
				} else {
					return property.get().getPropertyValue().recursiveGetValueByPath(paths);
				}
			} else {

				throw new NebulaException(String.format("Property '%s' not exists", path));
			}
		}
	}

	private boolean isFinalObject() {
		return generatedProperties == null;
	}
}
