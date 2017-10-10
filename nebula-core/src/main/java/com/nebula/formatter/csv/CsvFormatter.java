package com.nebula.formatter.csv;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.Property;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.ValueFormatter;

import java.util.List;

public class CsvFormatter implements Formatter {
    private final String separator;
    private final boolean header;
    private final String quote;
    private final List<String> columnNames;
    private final ValueFormatter valueFormatter;

    public CsvFormatter(String separator, String quote, boolean header, List<String> columnNames, ValueFormatter valueFormatter) {
        this.separator = separator;
        this.header = header;
        this.quote = quote;
        this.columnNames = columnNames;
        this.valueFormatter = valueFormatter;
    }

    public String formatGeneratedObject(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();

        formatObject(generatedObject, builder);

        return builder.toString();
    }

    private void formatObject(GeneratedObject generatedObject, StringBuilder builder) {
        boolean isFirst = true;

        for (String columnName : columnNames) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(separator);
            }
            Object valueToFormat = generatedObject.getGeneratedPropertyValue(columnName).getObject();
            String formattedValue = valueFormatter.formatValue(valueToFormat);
            appendValueWithQuotesToStringBuilder(builder, formattedValue);
        }

    }

    private void appendValueWithQuotesToStringBuilder(StringBuilder builder, Object value) {
        builder.append(quote).append(value).append(quote);
    }

    public String formatHeader(Entity entity) {

        addAllPropertiesToColumnNamesIfNoColumnNamesAreSpecified(entity);

        StringBuilder builder = new StringBuilder();
        if (header) {
            buildHeader(entity, builder);
        }
        return builder.toString();
    }

    private void addAllPropertiesToColumnNamesIfNoColumnNamesAreSpecified(Entity entity) {
        if (columnNames.isEmpty()) {
            for (Property property : entity.getProperties()) {
                columnNames.add(property.getName());
            }
        }
    }

    private void buildHeader(Entity entity, StringBuilder builder) {
        boolean isFirst = true;

        for (String columnName : columnNames) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(separator);
            }
            appendValueWithQuotesToStringBuilder(builder, columnName);
        }
    }

    public String formatFooter(Entity entity) {
        return "";
    }
}
