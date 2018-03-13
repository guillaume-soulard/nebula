package com.nebula.core.formatter.json;

import org.junit.Test;

public class JsonFormatterBuilderTest {

    @Test
    public void JsonFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').pretty().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    public void JsonFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withDateFormat("").pretty().withDateFormat(" ");

        // THEN
    }

    @Test
    public void JsonFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').pretty().withNumberDecimalSeparator(' ');

        // THEN
    }
}