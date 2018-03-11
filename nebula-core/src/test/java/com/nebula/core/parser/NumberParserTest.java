package com.nebula.core.parser;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberParserTest {

    @Test
    public void parse_should_return_0() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "0");

        // THEN
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void parse_should_return_10() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "10");

        // THEN
        assertThat(result).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void parse_should_return_10_dot_56() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "10.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("10.56"));
    }

    @Test
    public void parse_should_return_100000000_dot_56() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "100000000.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("100000000.56"));
    }

    @Test
    public void parse_should_return_100_comma_000_comma_000_dot_56() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel().build();

        // WHEN
        BigDecimal result = builder.parse(model, "100,000,000.56");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("100000000.56"));
    }

    @Test
    public void parse_should_return_number_with_custom_separators() throws Exception {

        // GIVEN
        NumberParser builder = new NumberParser();
        Model model = ModelBuilder.newModel()
                .withNumberThousandSeparator(' ')
                .withNumberDecimalSeparator(',')
                .build();

        // WHEN
        BigDecimal result = builder.parse(model, "1 234,96");

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("1234.96"));
    }
}