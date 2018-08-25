package com.nebula.core.formatter.sql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SqlFormatterBuilderTest {

    @Test
    @DisplayName("SqlFormatterBuilder should have method withNumberThousandSeparator")
    void SqlFormatterBuilder_should_have_method_withNumberThousandSeparator() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberThousandSeparator(' ');

        // THEN
    }

    @Test
    @DisplayName("SqlFormatterBuilder should have method withDateFormat")
    void SqlFormatterBuilder_should_have_method_withDateFormat() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withDateFormat("");

        // THEN
    }

    @Test
    @DisplayName("SqlFormatterBuilder should have method withNumberDecimalSeparator")
    void SqlFormatterBuilder_should_have_method_withNumberDecimalSeparator() {

        // GIVEN
        SqlFormatterBuilder builder= new SqlFormatterBuilder();

        // WHEN
        builder.withNumberDecimalSeparator(' ');

        // THEN
    }
}