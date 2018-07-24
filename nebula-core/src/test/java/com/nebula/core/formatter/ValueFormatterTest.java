package com.nebula.core.formatter;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ValueFormatterTest {

    @Test
    void formatValue_should_return_date_with_given_format() {

        // GIVEN
        ValueFormatter valueFormatter = new ValueFormatter("dd/MM/yyyy", '.', ',');
        DateTime dateToFormat = new DateTime(2000, 2, 1, 0, 0);

        // WHEN
        String result = valueFormatter.formatValue(dateToFormat);

        // THEN
        assertThat(result).isEqualTo("01/02/2000");
    }

    @Test
    void formatValue_should_return_number_with_correct_separators() {

        // GIVEN
        ValueFormatter valueFormatter = new ValueFormatter("dd/MM/yyyy", ',', ' ');
        BigDecimal number = new BigDecimal("1234567.89");

        // WHEN
        String result = valueFormatter.formatValue(number);

        // THEN
        assertThat(result).isEqualTo("1 234 567,89");
    }
}