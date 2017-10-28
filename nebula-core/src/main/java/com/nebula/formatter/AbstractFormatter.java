package com.nebula.formatter;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFormatter implements Formatter {
    protected final ValueFormatter valueFormatter;
    private final List<String> propertiesToExclude;

    public AbstractFormatter(ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        this.valueFormatter = valueFormatter;
        this.propertiesToExclude = propertiesToExclude;
    }

    protected GeneratedObject excludeFieldsOn(GeneratedObject generatedObject) {

        List<GeneratedProperty> propertiesToDelete = new ArrayList<>();
        if (generatedObject != null && generatedObject.getGeneratedProperties() != null) {
            for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
                if (propertiesToExclude.contains(generatedProperty.getPropertyName())) {
                    propertiesToDelete.add(generatedProperty);
                }
            }
            generatedObject.getGeneratedProperties().removeAll(propertiesToDelete);
        }

        return generatedObject;
    }
}
