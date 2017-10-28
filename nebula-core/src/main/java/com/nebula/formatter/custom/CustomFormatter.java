package com.nebula.formatter.custom;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.formatter.AbstractFormatter;
import com.nebula.formatter.ValueFormatter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomFormatter extends AbstractFormatter {
    private String headerFormat;
    private final String generatedObjectFormat;
    private String footerFormat;
    private final ValueFormatter valueFormatter;

    public CustomFormatter(String headerFormat, String generatedObjectFormat, String footerFormat, ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        super(valueFormatter, propertiesToExclude);
        this.headerFormat = headerFormat;
        this.generatedObjectFormat = generatedObjectFormat;
        this.footerFormat = footerFormat;
        this.valueFormatter = valueFormatter;
    }

    @Override
    public String formatHeader(Entity entity) {
        return headerFormat;
    }

    @Override
    public String formatGeneratedObject(GeneratedObject generatedObject) {

        generatedObject = excludeFieldsOn(generatedObject);
        String formattedString = generatedObjectFormat;
        Pattern pattern = Pattern.compile("#[^#]*#");
        Matcher matcher = pattern.matcher(generatedObjectFormat);

        while (matcher.find()) {
            String propertyName = matcher.group();
            propertyName = propertyName.substring(1, propertyName.length() - 1);
            String[] propertyPath = propertyName.split("\\.");
            Object valueToFormat = getValue(generatedObject, propertyPath, 0);
            String value = valueFormatter.formatValue(valueToFormat);
            formattedString = formattedString.replaceAll(matcher.group(), value);
        }

        return formattedString;
    }

    private Object getValue(GeneratedObject generatedObject, String[] propertyPath, int depth) {
        String propertyName = propertyPath[depth];
        Object object = generatedObject.getGeneratedPropertyValue(propertyName).getObject();
        List<GeneratedProperty> properties = generatedObject.getGeneratedPropertyValue(propertyName).getGeneratedProperties();

        if (object != null && properties == null) {
            return object;
        } else if (object == null && properties != null) {
            return getValue(generatedObject.getGeneratedPropertyValue(propertyName), propertyPath, depth + 1);
        } else {
            return null;
        }
    }

    @Override
    public String formatFooter(Entity entity) {
        return footerFormat;
    }
}
