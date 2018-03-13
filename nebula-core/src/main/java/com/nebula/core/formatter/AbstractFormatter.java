package com.nebula.core.formatter;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFormatter implements Formatter {
    protected final ValueFormatter valueFormatter;
    private final List<String> propertiesToExclude;

    public AbstractFormatter(ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        this.valueFormatter = valueFormatter;
        this.propertiesToExclude = propertiesToExclude;
    }

    protected GeneratedObject excludeFieldsOn(GeneratedObject generatedObject) {

        if (generatedObject != null && generatedObject.getGeneratedProperties() != null) {
            List<GeneratedProperty> propertiesToDelete = generatedObject.getGeneratedProperties()
                    .stream()
                    .filter(generatedProperty ->
                            propertiesToExclude.contains(generatedProperty.getPropertyName())).collect(Collectors.toList());
            generatedObject.getGeneratedProperties().removeAll(propertiesToDelete);
        }

        return generatedObject;
    }
}
