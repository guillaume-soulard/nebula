package com.nebula.formatter.csv;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.Property;
import com.nebula.formatter.AbstractFormatter;
import com.nebula.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class CsvFormatter extends AbstractFormatter {
    private final String separator;
    private final boolean header;
    private final String quote;
    private final List<String> columnNames;

    CsvFormatter(String separator, String quote, boolean header, List<String> columnNames, ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        super(valueFormatter, propertiesToExclude);
        this.separator = separator;
        this.header = header;
        this.quote = quote;
        this.columnNames = columnNames;
    }

    public String formatGeneratedObject(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();

        formatObject(excludeFieldsOn(generatedObject), builder);

        return builder.toString();
    }

    private void formatObject(GeneratedObject generatedObject, StringBuilder builder) {
        boolean isFirst = true;

        List<String> columnNamesToUse = null;

        if (columnNames == null || columnNames.isEmpty()) {
            columnNamesToUse = getAllColumnsOfGeneratedObject(generatedObject);
        } else {
            columnNamesToUse = columnNames;
        }

        for (String columnName : columnNamesToUse) {
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

    private List<String> getAllColumnsOfGeneratedObject(GeneratedObject generatedObject) {
        List<String> columns = new ArrayList<>();

        for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
            columns.add(generatedProperty.getPropertyName());
        }

        return columns;
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
