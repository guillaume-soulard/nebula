package com.nebula.formatter.sql;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.formatter.AbstractFormatter;
import com.nebula.formatter.ValueFormatter;
import org.joda.time.DateTime;

import java.util.List;

class SqlFormatter extends AbstractFormatter {

    private String tableName;

    SqlFormatter(ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        super(valueFormatter, propertiesToExclude);
    }

    @Override
    public String formatHeader(Entity entity) {
        tableName = entity.getName();
        return "DELETE FROM " + entity.getName() + ";";
    }

    @Override
    public String formatGeneratedObject(GeneratedObject generatedObject) {
        return "INSERT INTO " + tableName + " (" + getFields(generatedObject) + ") VALUES (" + getValues(generatedObject) + ");";
    }

    @Override
    public String formatFooter(Entity entity) {
        return "COMMIT;";
    }

    private String getValues(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();

        if (generatedObject.getObject() == null) {
            boolean isFirst = true;
            for (GeneratedProperty generatedProperty : excludeFieldsOn(generatedObject).getGeneratedProperties()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(",");
                }
                Object valueToFormat = generatedProperty.getPropertyValue().getObject();
                String formattedValue = valueFormatter.formatValue(valueToFormat);

                if (valueToFormat instanceof DateTime ||
                        valueToFormat instanceof String) {
                    builder.append("'").append(formattedValue).append("'");
                } else {
                    builder.append(formattedValue);
                }
            }
        } else {
            builder.append(generatedObject.getObject());
        }

        return builder.toString();
    }

    private String getFields(GeneratedObject generatedObject) {
        StringBuilder builder = new StringBuilder();

        if (generatedObject.getObject() == null) {
            boolean isFirst = true;
            for (GeneratedProperty generatedProperty : excludeFieldsOn(generatedObject).getGeneratedProperties()) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(",");
                }
                builder.append(generatedProperty.getPropertyName());
            }
        } else {
            builder.append(generatedObject.getObject());
        }

        return builder.toString();
    }
}
