package com.nebula.core;

import com.nebula.Model;
import com.nebula.core.generators.Generator;
import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class PropertyBuilder {

	public Property newProperty(Model model, String propertyName, TypeBuilder propertyType,
								GeneratorBuilder propertyGeneratorBuilder) {
		checkIfPropertyNameIsNotNull(propertyName);
		checkIfPropertyTypeIsNotNull(propertyType);
		checkIfPropertyGeneratorIsNotNull(propertyGeneratorBuilder);
		return new Property(propertyName, buildType(model, propertyType),
				buildGenerator(propertyGeneratorBuilder));
	}

	private Generator buildGenerator(GeneratorBuilder propertyGeneratorBuilder) {
		return propertyGeneratorBuilder.build();
	}

	private Type buildType(Model model, TypeBuilder propertyType) {
		return propertyType.build(model);
	}

	private void checkIfPropertyGeneratorIsNotNull(GeneratorBuilder propertyGeneratorBuilder) {
		if (propertyGeneratorBuilder == null) {
			throw new NebulaException("property generator is null");
		}
	}

	private void checkIfPropertyTypeIsNotNull(TypeBuilder propertyType) {
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