package com.nebula.core;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GeneratedProperties {

    private List<GeneratedProperty> generatedProperties;

    public GeneratedProperties(List<GeneratedProperty> generatedProperties) {
        this.generatedProperties = generatedProperties;
    }

    public List<GeneratedProperty> getGeneratedProperties() {
        return generatedProperties;
    }

    public Optional<GeneratedProperty> getPropertyByName(String propertyName) {

        return generatedProperties.stream().filter(p -> p.getPropertyName().equals(propertyName)).findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneratedProperties that = (GeneratedProperties) o;
        return Objects.equals(generatedProperties, that.generatedProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generatedProperties);
    }
}
