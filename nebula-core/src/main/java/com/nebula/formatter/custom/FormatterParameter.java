package com.nebula.formatter.custom;

import com.nebula.core.GeneratedObject;
import com.nebula.formatter.ValueFormatter;

import java.util.List;

public class FormatterParameter {

    private List<String> propertiesToIgnore;
    private ValueFormatter valueFormatter;
    private GeneratedObject generatedObject;

    public FormatterParameter(List<String> propertiesToIgnore, ValueFormatter valueFormatter) {
        this.propertiesToIgnore = propertiesToIgnore;
        this.valueFormatter = valueFormatter;
    }

    public List<String> getPropertiesToIgnore() {
        return propertiesToIgnore;
    }

    public ValueFormatter getValueFormatter() {
        return valueFormatter;
    }
}
