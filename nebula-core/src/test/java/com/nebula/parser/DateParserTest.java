package com.nebula.parser;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DateParserTest {

    @Test
    public void parse_should_return_01_01_2017() throws Exception {

        // GIVEN
        DateParser dateParser = new DateParser();
        String dateToParse = "01/01/2017";
        Model model = new ModelBuilder().build();

        // WHEN
        ReadableInstant result = dateParser.parse(model, dateToParse);

        // THEN
        assertThat(result).isEqualTo(new DateTime(2017, 1, 1, 0, 0));
    }

    @Test
    public void parse_should_return_12_31_2017() throws Exception {

        // GIVEN
        DateParser dateParser = new DateParser();
        String dateToParse = "12/31/2017";
        Model model = new ModelBuilder().build();

        // WHEN
        ReadableInstant result = dateParser.parse(model, dateToParse);

        // THEN
        assertThat(result).isEqualTo(new DateTime(2017, 12, 31, 0, 0));
    }

    @Test
    public void parse_should_return_12_31_2017_with_custom_format() throws Exception {

        // GIVEN
        DateParser dateParser = new DateParser();
        String dateToParse = "31-12-2017";
        Model model = new ModelBuilder().withDateFormat("dd-MM-yyyy").build();

        // WHEN
        ReadableInstant result = dateParser.parse(model, dateToParse);

        // THEN
        assertThat(result).isEqualTo(new DateTime(2017, 12, 31, 0, 0));
    }
}