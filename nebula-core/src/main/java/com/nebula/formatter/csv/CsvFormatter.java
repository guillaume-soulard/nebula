package com.nebula.formatter.csv;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.formatter.Formatter;

public class CsvFormatter implements Formatter {
    private final String separator;
    private final boolean header;
    private final String quote;

    public CsvFormatter(String separator, String quote, boolean header) {
        this.separator = separator;
        this.header = header;
        this.quote = quote;
    }

    public CsvFormatter() {
        this(",", "", true);
    }

    public String formatGeneratedObject(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();

        formatObject(generatedObject, builder);

        return builder.toString();
    }

    private void formatObject(GeneratedObject generatedObject, StringBuilder builder) {
        boolean isFirst = true;

        for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(separator);
            }
            appendValueWithQuotesToStringBuilder(builder, generatedProperty.getPropertyValue().getObject());
        }
    }

    private void appendValueWithQuotesToStringBuilder(StringBuilder builder, Object value) {
        builder.append(quote).append(value).append(quote);
    }

    public String formatHeader(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();
        if (header) {
            buildHeader(generatedObject, builder);
        }
        return builder.toString();
    }

    private void buildHeader(GeneratedObject generatedObject, StringBuilder builder) {
        boolean isFirst = true;
        for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(separator);
            }
            appendValueWithQuotesToStringBuilder(builder, generatedProperty.getPropertyName());
        }
    }

    public String formatFooter(GeneratedObject generatedObject) {
        return "";
    }
}
