package com.nebula.formatter.json;

import com.nebula.Model;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.formatter.ValueFormatter;

public class JsonFormatterBuilder implements FormatterBuilder {
    private boolean prettyFormat = false;
    private boolean quotedFields = false;
    private String dateFormat;
    private Character numberDecimalSeparator;
    private Character numberThousandSeparator;

    @Override
    public Formatter build(Model model) {

        if (dateFormat == null) {
            dateFormat = model.getDateFormat();
        }

        if (numberDecimalSeparator == null) {
            numberDecimalSeparator = model.getNumberDecimalSeparator();
        }

        if (numberThousandSeparator == null) {
            numberThousandSeparator = model.getNumberThousandSeparator();
        }

        return new JsonFormatter(prettyFormat, quotedFields, new ValueFormatter(dateFormat, numberDecimalSeparator, numberThousandSeparator));
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
