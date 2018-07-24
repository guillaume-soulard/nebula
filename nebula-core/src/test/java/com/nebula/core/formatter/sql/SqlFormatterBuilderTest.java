package com.nebula.core.formatter.sql;

import org.junit.jupiter.api.Test;

class SqlFormatterBuilderTest {

    @Test
    void SqlFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    void SqlFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withDateFormat("");

        // THEN
    }

    @Test
    void SqlFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ');

        // THEN
    }
}