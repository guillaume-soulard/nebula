package com.nebula.formatter;

import com.nebula.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFormatterBuilder implements FormatterBuilder {

    private Character numberThousandSeparator;
    private Character numberDecimalSeparator;
    private String dateFormat;
    protected List<String> propertiesToExclude = new ArrayList<>();

    protected ValueFormatter buildValueFormatter(Model model) {
        if (dateFormat == null) {
            dateFormat = model.getDateFormat();
        }

        if (numberDecimalSeparator == null) {
            numberDecimalSeparator = model.getNumberDecimalSeparator();
        }

        if (numberThousandSeparator == null) {
            numberThousandSeparator = model.getNumberThousandSeparator();
        }

        return new ValueFormatter(dateFormat, numberDecimalSeparator, numberThousandSeparator);
    }

    public AbstractFormatterBuilder withDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public AbstractFormatterBuilder withNumberDecimalSeparator(char numberDecimalSeparator) {
        this.numberDecimalSeparator = numberDecimalSeparator;
        return this;
    }

    public AbstractFormatterBuilder withNumberThousandSeparator(char numberThousandSeparator) {
        this.numberThousandSeparator = numberThousandSeparator;
        return this;
    }

    public AbstractFormatterBuilder addPropertyToIgnore(String propertyName) {
        this.propertiesToExclude.add(propertyName);
        return this;
    }
}
