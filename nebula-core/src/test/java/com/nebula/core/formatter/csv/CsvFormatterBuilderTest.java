package com.nebula.core.formatter.csv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CsvFormatterBuilderTest {

    @Test
    @DisplayName("CsvFormatterBuilder should have method withNumberThousandSeparator")
    void CsvFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').withHeader().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    @DisplayName("CsvFormatterBuilder should have method withDateFormat")
    void CsvFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        CsvFormatterBuilder builder= new CsvFormatterBuilder();

        // WHEN
        builder.withDateFormat("").withHeader().withDateFormat(" ");

        // THEN
    }

    @Test
    @DisplayName("CsvFormatterBuilder should have method withNumberDecimalSeparator")
    void CsvFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        CsvFormatterBuilder builder = new CsvFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').withHeader().withNumberDecimalSeparator(' ');

        // THEN
    }
}