package com.nebula.formatter.json;

import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.formatter.ValueFormatter;

import java.util.List;

public class JsonFormatterBuilder extends AbstractFormatterBuilder {
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
}
