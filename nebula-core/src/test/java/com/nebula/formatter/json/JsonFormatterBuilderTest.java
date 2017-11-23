package com.nebula.formatter.json;

import org.junit.Test;

public class JsonFormatterBuilderTest {

    @Test
    public void JsonFormatterBuilder_should_have_method_withNumberThousandSeparator() throws Exception {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').pretty().withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    public void JsonFormatterBuilder_should_have_method_withDateFormat() throws Exception {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withDateFormat("").pretty().withDateFormat(" ");

        // THEN
    }

    @Test
    public void JsonFormatterBuilder_should_have_method_withNumberDecimalSeparator() throws Exception {

        // GIVEN
        JsonFormatterBuilder builder= new JsonFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').pretty().withNumberDecimalSeparator(' ');

        // THEN
    }
}