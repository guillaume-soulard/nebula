package com.nebula.core;

import com.nebula.core.generators.GeneratorBuilder;
import com.nebula.core.generators.NebulaRandom;
import com.nebula.core.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nebula.core.generators.NebulaGenerators.random;

public class Entity implements Type {

	private PropertyBuilder propertyBuilder;
	private List<Property> properties = new ArrayList<>();
	private String name;
	private Long amount;
	private Model model;

	public Entity(Model model, String name, Long amount, PropertyBuilder propertyBuilder) {
		this.amount = amount;
		this.propertyBuilder = propertyBuilder;
		this.name = name;
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public Entity addProperty(String propertyName, GeneratorBuilder propertyGeneratorBuilder, RandomTypeBuilder propertyType) {
        return addProperty(propertyName, propertyType, propertyGeneratorBuilder);
	}

	public Entity addProperty(String propertyName, StaticTypeBuilder propertyType) {
		return addProperty(propertyName, propertyType, random());
	}

	private Entity addProperty(String propertyName, TypeBuilder propertyType, GeneratorBuilder propertyGeneratorBuilder) {
        checkIfPropertyAlreadyExists(propertyName);
        properties.add(propertyBuilder.newProperty(model, propertyName, propertyType, propertyGeneratorBuilder));
        return this;
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
		return new GenerationContext(new NebulaRandom(getPropertySeed(context, property)), context.getModel(), context.getEntityIndex());
	}

	private long getPropertySeed(GenerationContext context, Property property) {
		long seed = context.getNebulaRandom().getSeed();
		int propertyHashCode = property.getName().hashCode();
		return seed + propertyHashCode;
	}

	public GeneratedObject generateObject(Long objectIndex) {
		List<GeneratedProperty> generatedProperties = properties.stream().map(property -> new GeneratedProperty(property.getName(),
                property.getGenerator().generate(property.getType()),
                property.getType())).collect(Collectors.toList());
        return new GeneratedObject(generatedProperties);
	}

	public Long getMaxRange() {
		return amount;
	}

	@Override
	public JavaType getJavaType() {
		return new JavaType(name);
	}

	public Long getMinRange() {
		return 1L;
	}

	public void setPropertyBuilder(PropertyBuilder propertyBuilder) {
		this.propertyBuilder = propertyBuilder;
	}

	public Long getAmount() {
		return amount;
	}
}
