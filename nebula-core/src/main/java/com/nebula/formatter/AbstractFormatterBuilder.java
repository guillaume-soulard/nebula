package com.nebula.formatter;

import com.nebula.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFormatterBuilder<T extends AbstractFormatterBuilder> implements FormatterBuilder {

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

    public T withDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return getThis();
    }

    public T withNumberDecimalSeparator(char numberDecimalSeparator) {
        this.numberDecimalSeparator = numberDecimalSeparator;
        return getThis();
    }

    public T withNumberThousandSeparator(char numberThousandSeparator) {
        this.numberThousandSeparator = numberThousandSeparator;
        return getThis();
    }

    public T addPropertyToIgnore(String propertyName) {
        this.propertiesToExclude.add(propertyName);
        return getThis();
    }

    protected abstract T getThis();
}
