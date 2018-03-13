package com.nebula.core.formatter.json;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.formatter.AbstractFormatter;
import com.nebula.core.formatter.ValueFormatter;

import java.util.Collection;
import java.util.List;

class JsonFormatter extends AbstractFormatter {

    private static final String JSON_OBJECT_OPEN = "{";
    private static final String JSON_OBJECT_CLOSE = "}";
    private static final String JSON_OBJECT_SEPARATOR = ",";
    private static final String JSON_PROPERTY_AND_VALUE_SEPARATOR = ":";
    private static final String JSON_NULL = "null";
    private static final String QUOTE = "\"";
    private static final String JSON_PRETTY_FORMAT_INDENTATION = "  ";
    private static final String JSON_PRETTY_FORMAT_SPACE = " ";

    private final boolean prettyFormat;
    private final boolean quotedFields;

    JsonFormatter(boolean prettyFormat, boolean quotedFields, ValueFormatter valueFormatter, List<String> propertiesToExclude) {
        super(valueFormatter, propertiesToExclude);
        this.prettyFormat = prettyFormat;
        this.quotedFields = quotedFields;
    }

    @Override
    public String formatHeader(Entity entity) {
        return "";
    }

    @Override
    public String formatGeneratedObject(GeneratedObject generatedObject) {
        return formatObject(excludeFieldsOn(generatedObject), 1);
    }

    private String formatObject(GeneratedObject generatedObject, int depth) {
        if (isSimpleValue(generatedObject)) {
            return getSimpleValue(generatedObject);
        } else if (isArray(generatedObject)) {
            return getArrayValue(generatedObject, depth);
        } else if (isObject(generatedObject)) {
            return getObjectValue(generatedObject, depth);
        } else {
            return JSON_NULL;
        }
    }

    private String getArrayValue(GeneratedObject generatedObject, int depth) {

        List<GeneratedObject> items = (List<GeneratedObject>) generatedObject.getObject();
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;

        stringBuilder.append("[");

        for (GeneratedObject item : items) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(JSON_OBJECT_SEPARATOR);
            }
            stringBuilder.append(formatObject(item, depth));
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private boolean isArray(GeneratedObject generatedObject) {
        return generatedObject.getObject() != null
                && generatedObject.getObject() instanceof Collection;
    }

    private boolean isObject(GeneratedObject generatedObject) {
        return generatedObject.getGeneratedProperties() != null;
    }

    private boolean isSimpleValue(GeneratedObject generatedObject) {
        return generatedObject.getObject() != null
                && !(generatedObject.getObject() instanceof Collection);
    }

    private String getObjectValue(GeneratedObject generatedObject, int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        stringBuilder.append(JSON_OBJECT_OPEN);
        addLineSeparatorIfPrettyRequested(stringBuilder);

        for (GeneratedProperty generatedProperty : generatedObject.getGeneratedProperties()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(JSON_OBJECT_SEPARATOR);
                addLineSeparatorIfPrettyRequested(stringBuilder);
            }
            indentJsonObjectPropertiesIfPrettyRequested(depth, stringBuilder);
            addQuoteIfNeeded(stringBuilder);
            stringBuilder.append(generatedProperty.getPropertyName());
            addQuoteIfNeeded(stringBuilder);
            stringBuilder.append(JSON_PROPERTY_AND_VALUE_SEPARATOR);
            addSpaceAfterFieldIfPrettyRequested(stringBuilder);
            stringBuilder.append(formatObject(generatedProperty.getPropertyValue(), depth + 1));
        }

        addLineSeparatorIfPrettyRequested(stringBuilder);
        if (prettyFormat) {
            indentJsonObjectIfPrettyRequested(depth, stringBuilder);
        }
        stringBuilder.append(JSON_OBJECT_CLOSE);

        return stringBuilder.toString();
    }

    private void addSpaceAfterFieldIfPrettyRequested(StringBuilder stringBuilder) {
        if (prettyFormat) {
            stringBuilder.append(JSON_PRETTY_FORMAT_SPACE);
        }
    }

    private void addQuoteIfNeeded(StringBuilder stringBuilder) {
        if (quotedFields) {
            stringBuilder.append(QUOTE);
        }
    }

    private void indentJsonObjectIfPrettyRequested(int depth, StringBuilder stringBuilder) {
        for (int i = 0; i < depth - 1; i++) {
            stringBuilder.append(JSON_PRETTY_FORMAT_INDENTATION);
        }
    }

    private void indentJsonObjectPropertiesIfPrettyRequested(int depth, StringBuilder stringBuilder) {
        if (prettyFormat) {
            for (int i = 0; i < depth; i++) {
                stringBuilder.append(JSON_PRETTY_FORMAT_INDENTATION);
            }
        }
    }

    private void addLineSeparatorIfPrettyRequested(StringBuilder stringBuilder) {
        if (prettyFormat) {
            stringBuilder.append(System.lineSeparator());
        }
    }

    private String getSimpleValue(GeneratedObject generatedObject) {
        if (generatedObject.getObject() instanceof String) {
            return QUOTE + generatedObject.getObject().toString() + QUOTE;
        } else {
            return valueFormatter.formatValue(generatedObject.getObject());
        }
    }

    @Override
    public String formatFooter(Entity entity) {
        return "";
    }
}
