package com.nebula.core.formatter.csv;

import org.junit.Test;

public class CsvFormatterBuilderTest {

    @Test
    public void CsvFormatterBuilder_should_have_method_withNumberThousandSeparator() throws Exception {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').withHeader().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    public void CsvFormatterBuilder_should_have_method_withDateFormat() throws Exception {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withDateFormat("").withHeader().withDateFormat(" ");

        // THEN
    }

    @Test
    public void CsvFormatterBuilder_should_have_method_withNumberDecimalSeparator() throws Exception {

        // GIVEN
        CsvFormatterBuilder builder = new CsvFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').withHeader().withNumberDecimalSeparator(' ');

        // THEN
    }
}