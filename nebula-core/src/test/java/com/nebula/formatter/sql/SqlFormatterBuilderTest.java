package com.nebula.formatter.sql;

import org.junit.Test;

public class SqlFormatterBuilderTest {

    @Test
    public void SqlFormatterBuilder_should_have_method_withNumberThousandSeparator() throws Exception {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    public void SqlFormatterBuilder_should_have_method_withDateFormat() throws Exception {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withDateFormat("");

        // THEN
    }

    @Test
    public void SqlFormatterBuilder_should_have_method_withNumberDecimalSeparator() throws Exception {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ');

        // THEN
    }
}