package com.nebula.core;

import java.util.ArrayList;
import java.util.List;

import com.nebula.core.generators.Generator;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.Type;
import com.nebula.core.types.TypeBuilder;

public class Entity implements Type {

	private PropertyBuilder propertyBuilder = null;
	private List<Property> properties = new ArrayList<Property>();
	private String name;
	private Long amount;

	public Entity(String name, Long amount, PropertyBuilder propertyBuilder) throws NebulaException {
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

	public void addProperty(String propertyName, TypeBuilder propertyType, Generator propertyGenerator)
			throws NebulaException {
		checkIfPropertyAlreadyExists(propertyName);
		properties.add(propertyBuilder.newProperty(propertyName, propertyType, propertyGenerator));
	}

	private void checkIfPropertyAlreadyExists(String propertyName) throws NebulaException {
		for (Property property : properties) {
			if (property.getName().equals(propertyName)) {
				throw new NebulaException("duplicate property '" + propertyName + "' in entity '" + name + "'");
			}
		}
	}

	public void init(NebulaRandom nebulaRandom) throws NebulaException {
		for (Property property : properties) {
			property.getGenerator().init(nebulaRandom);
		}
	}

	public GeneratedObject generateObject(Long objectIndex) throws NebulaException {
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
