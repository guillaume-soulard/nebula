package com.nebula.core;

import java.util.ArrayList;
import java.util.List;

public class StaticEntityRecord {

    private List<StaticEntityProperty> staticEntityProperties = new ArrayList<>();

    public void addProperty(String name, Object value) {

        staticEntityProperties.add(new StaticEntityProperty(name, value));
    }

    public Object getPropertyValue(String propertyName) {

        return staticEntityProperties
                .stream()
                .filter(p -> p.getPropertyName().equals(propertyName))
                .findFirst()
                .orElse(null);
    }

    public List<StaticEntityProperty> getStaticEntityProperties() {
        return staticEntityProperties;
    }
}
