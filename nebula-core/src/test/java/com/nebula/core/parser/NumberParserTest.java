package com.nebula.core.parser;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NumberParserTest {

    @Test
    @DisplayName("parse should return 0")
    void parse_should_return_0() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "0");

        // THEN
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("parse should return 10")
    void parse_should_return_10() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "10");

        // THEN
        assertThat(result).isEqualTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("parse should return 10 dot 56")
    void parse_should_return_10_dot_56() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "10.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("10.56"));
    }

    @Test
    @DisplayName("parse should return 100000000 dot 56")
    void parse_should_return_100000000_dot_56() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "100000000.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("100000000.56"));
    }

    @Test
    @DisplayName("parse should return 100 comma 000 comma 000 dot 56")
    void parse_should_return_100_comma_000_comma_000_dot_56() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "100,000,000.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("100000000.56"));
    }

    @Test
    @DisplayName("parse should return number with custom separators")
    void parse_should_return_number_with_custom_separators() {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newEmptyModel()
                .withNumberThousandSeparator(' ')
                .withNumberDecimalSeparator(',')
                .build();

        // WHEN
        BigDecimal result = builder.parse(model, "1 234,96");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("1234.96"));
    }
}