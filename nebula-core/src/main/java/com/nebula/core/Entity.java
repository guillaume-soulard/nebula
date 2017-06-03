package com.nebula.core;

import java.util.ArrayList;
import java.util.List;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.GenerationContext;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class Entity implements Type {

	private PropertyBuilder propertyBuilder = null;
	private List<Property> properties = new ArrayList<Property>();
	private String name;
	private Long amount;

	public Entity(String name, Long amount, PropertyBuilder propertyBuilder) {
		this.amount = amount;
		this.propertyBuilder = propertyBuilder;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void addProperty(String propertyName, GeneratorBuilder propertyGeneratorBuilder, TypeBuilder propertyType) {
		checkIfPropertyAlreadyExists(propertyName);
		properties.add(propertyBuilder.newProperty(propertyName, propertyType, propertyGeneratorBuilder));
	}

	private void checkIfPropertyAlreadyExists(String propertyName) {
		for (Property property : properties) {
			if (property.getName().equals(propertyName)) {
				throw new NebulaException("duplicate property '" + propertyName + "' in entity '" + name + "'");
			}
		}
	}

	public void init(GenerationContext context) {
		for (Property property : properties) {
			GenerationContext propertyGenerationContext = buildPropertyGenerationContext(context, property);
			property.getGenerator().init(propertyGenerationContext);
			property.getType().init(propertyGenerationContext);
		}
	}

	private GenerationContext buildPropertyGenerationContext(GenerationContext context, Property property) {
		return new GenerationContext(new NebulaRandom(getPropertySeed(context, property)), context.getModel());
	}

	private long getPropertySeed(GenerationContext context, Property property) {
		long seed = context.getNebulaRandom().getSeed();
		int propertyHashCode = property.getName().hashCode();
		return seed + propertyHashCode;
	}

	public GeneratedObject generateObject(Long objectIndex) {
		List<GeneratedProperty> generatedProperties = new ArrayList<GeneratedProperty>();
		for (Property property : properties) {

			generatedProperties.add(
					new GeneratedProperty(property.getName(), property.getGenerator().generate(property.getType())));
		}
		return new GeneratedObject(generatedProperties);
	}

	public Long getMaxRange() {
		return amount;
	}

	public Long getMinRange() {
		return 1l;
	}

	public void setPropertyBuilder(PropertyBuilder propertyBuilder) {
		this.propertyBuilder = propertyBuilder;
	}

	public Long getAmount() {
		return amount;
	}
}
