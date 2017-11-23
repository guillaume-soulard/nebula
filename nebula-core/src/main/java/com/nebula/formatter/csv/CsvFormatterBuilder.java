package com.nebula.formatter.csv;


import com.nebula.Model;
import com.nebula.formatter.AbstractFormatterBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFormatterBuilder extends AbstractFormatterBuilder<CsvFormatterBuilder> {
    private String separator = ",";
    private String quote = "";
    private boolean header = true;
    private List<String> columnNames;
    private String dateFormat;
    private Character numberDecimalSeparator;
    private Character numberThousandSeparator;

    @Override
    public CsvFormatter build(Model model) {

        if (dateFormat == null) {
            dateFormat = model.getDateFormat();
        }

        if (numberDecimalSeparator == null) {
            numberDecimalSeparator = model.getNumberDecimalSeparator();
        }

        if (numberThousandSeparator == null) {
            numberThousandSeparator = model.getNumberThousandSeparator();
        }

        if (columnNames == null) {
            columnNames = new ArrayList<>();
        }

        return new CsvFormatter(separator, quote, header, columnNames, buildValueFormatter(model), propertiesToExclude);
    }

    public CsvFormatterBuilder withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public CsvFormatterBuilder withQuote(String quote) {
        this.quote = quote;
        return this;
    }

    public CsvFormatterBuilder withHeader() {
        this.header = true;
        return this;
    }

    public CsvFormatterBuilder withoutHeader() {
        this.header = false;
        return this;
    }

    public CsvFormatterBuilder withColumns(String... columnNames) {
        this.columnNames = Arrays.asList(columnNames);
        return this;
    }

    @Override
    protected CsvFormatterBuilder getThis() {
        return this;
    }
}
