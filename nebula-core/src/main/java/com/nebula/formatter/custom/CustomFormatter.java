package com.nebula.formatter.custom;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.ValueFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomFormatter implements Formatter {
    private String headerFormat;
    private final String generatedObjectFormat;
    private String footerFormat;
    private final ValueFormatter valueFormatter;

    public CustomFormatter(String headerFormat, String generatedObjectFormat, String footerFormat, ValueFormatter valueFormatter) {
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

        String formattedString = generatedObjectFormat;

        Pattern pattern = Pattern.compile("#[^#]*#");

        Matcher matcher = pattern.matcher(generatedObjectFormat);

        while (matcher.find()) {
            String propertyName = matcher.group();
            propertyName = propertyName.substring(1, propertyName.length() - 1);
            String value = valueFormatter.formatValue(generatedObject.getGeneratedPropertyValue(propertyName).getObject());
            formattedString = formattedString.replaceAll(matcher.group(), value);
        }

        return formattedString;
    }

    @Override
    public String formatFooter(Entity entity) {
        return footerFormat;
    }
}
