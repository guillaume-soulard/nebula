package com.nebula.formatter.json;

import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;

public class JsonFormatterBuilder extends AbstractFormatterBuilder<JsonFormatterBuilder> {
    private boolean prettyFormat = false;
    private boolean quotedFields = false;

    @Override
    public Formatter build(Model model) {

        return new JsonFormatter(prettyFormat, quotedFields, buildValueFormatter(model), propertiesToExclude);
    }

    public JsonFormatterBuilder pretty() {
        prettyFormat = true;
        return this;
    }

    public JsonFormatterBuilder quotedFields() {
        quotedFields = true;
        return this;
    }

    @Override
    protected JsonFormatterBuilder getThis() {
        return this;
    }
}
