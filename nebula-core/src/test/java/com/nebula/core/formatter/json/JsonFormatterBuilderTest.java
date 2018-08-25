package com.nebula.core.formatter.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonFormatterBuilderTest {

    @Test
    @DisplayName("JsonFormatterBuilder should have method withNumberThousandSeparator")
    void JsonFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').pretty().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    @DisplayName("JsonFormatterBuilder should have method withDateFormat")
    void JsonFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withDateFormat("").pretty().withDateFormat(" ");

        // THEN
    }

    @Test
    @DisplayName("JsonFormatterBuilder should have method withNumberDecimalSeparator")
    void JsonFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').pretty().withNumberDecimalSeparator(' ');

        // THEN
    }
}