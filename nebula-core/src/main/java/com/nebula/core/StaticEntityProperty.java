package com.nebula.core;

public class StaticEntityProperty {

    private String propertyName;
    private Object propertyValue;

    public StaticEntityProperty(String propertyName, Object propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }
}
