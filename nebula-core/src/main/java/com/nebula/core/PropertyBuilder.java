package com.nebula.core;

import com.nebula.core.generators.Generator;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class PropertyBuilder {

	public Property newProperty(String propertyName, TypeBuilder propertyType, Generator propertyGenerator) {
		checkIfPropertyNameIsNotNull(propertyName);
		checkIfPropertyTyapeIsNotNull(propertyType);
		checkIfPropertyGeneratorIsNotNull(propertyGenerator);
		Property property = new Property(propertyName, buildType(propertyType), propertyGenerator);
		return property;
	}

	private Type buildType(TypeBuilder propertyType) {
		return propertyType.build();
	}

	private void checkIfPropertyGeneratorIsNotNull(Generator propertyGenerator) {
		if (propertyGenerator == null) {
			throw new NebulaException("property generator is null");
		}
	}

	private void checkIfPropertyTyapeIsNotNull(TypeBuilder propertyType) {
		if (propertyType == null) {
			throw new NebulaException("property type is null");
		}
	}

	private void checkIfPropertyNameIsNotNull(String propertyName) {
		if (propertyName == null) {
			throw new NebulaException("property name is null");
		}
	}
}