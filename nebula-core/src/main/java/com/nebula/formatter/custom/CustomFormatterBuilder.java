package com.nebula.formatter.custom;

import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.formatter.ValueFormatter;

import java.util.List;

public class CustomFormatterBuilder extends AbstractFormatterBuilder {

    private String headerFormat;
    private String generatedObjectFormat;
    private String footerFormat;
    private Character numberThousandSeparator;
    private Character numberDecimalSeparator;
    private String dateFormat;

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

        return new CustomFormatter(headerFormat, generatedObjectFormat, footerFormat, buildValueFormatter(model), propertiesToExclude);
    }

    public CustomFormatterBuilder withHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
        return this;
    }

    public CustomFormatterBuilder withGeneratedObjectFormat(String generatedObjectFormat) {
        this.generatedObjectFormat = generatedObjectFormat;
        return this;
    }

    public CustomFormatterBuilder withFooterFormat(String footerFormat) {
        this.footerFormat = footerFormat;
        return this;
    }
}
