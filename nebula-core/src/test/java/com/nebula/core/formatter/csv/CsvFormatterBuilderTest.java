package com.nebula.core.formatter.csv;

import org.junit.jupiter.api.Test;

class CsvFormatterBuilderTest {

    @Test
    void CsvFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').withHeader().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    void CsvFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withDateFormat("").withHeader().withDateFormat(" ");

        // THEN
    }

    @Test
    void CsvFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        CsvFormatterBuilder builder = new CsvFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').withHeader().withNumberDecimalSeparator(' ');

        // THEN
    }
}