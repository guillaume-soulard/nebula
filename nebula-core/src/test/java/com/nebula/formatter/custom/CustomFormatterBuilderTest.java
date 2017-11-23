package com.nebula.formatter.custom;

import org.junit.Test;

public class CustomFormatterBuilderTest {

    @Test
    public void CustomFormatterBuilder_should_have_method_withNumberThousandSeparator() throws Exception {

        // GIVEN
        CustomFormatterBuilder builder= new CustomFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ').withFooterFormat("").withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    public void CustomFormatterBuilder_should_have_method_withDateFormat() throws Exception {

        // GIVEN
        CustomFormatterBuilder builder= new CustomFormatterBuilder();

        // WHEN
        builder.withDateFormat("").withFooterFormat("").withDateFormat(" ");

        // THEN
    }

    @Test
    public void CustomFormatterBuilder_should_have_method_withNumberDecimalSeparator() throws Exception {

        // GIVEN
        CustomFormatterBuilder builder= new CustomFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ').withFooterFormat("").withNumberDecimalSeparator(' ');

        // THEN
    }
}