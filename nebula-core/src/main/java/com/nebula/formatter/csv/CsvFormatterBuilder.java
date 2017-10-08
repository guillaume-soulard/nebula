package com.nebula.formatter.csv;


import com.nebula.formatter.Formatter;
import com.nebula.formatter.FormatterBuilder;

public class CsvFormatterBuilder implements FormatterBuilder {
    private String separator = ",";
    private String quote = "";
    private boolean header = true;

    @Override
    public Formatter build() {
        return new CsvFormatter(separator, quote, header);
    }

    public CsvFormatterBuilder withSepatator(String separator) {
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
}
