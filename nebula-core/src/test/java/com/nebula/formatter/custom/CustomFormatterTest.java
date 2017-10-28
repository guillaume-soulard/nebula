package com.nebula.formatter.custom;

import com.nebula.Nebula;
import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.formatter.ValueFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.nebula.core.NebulaGenerationTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomFormatterTest {

    private ValueFormatter valueFormatter = new ValueFormatter("MM/dd/yyyy", '.', ',');;

    @Test
    public void formatGeneratedObject_should_return_given_format() throws Exception {

        // GIVEN
        String format = "static format";
        String headerFormat = "test";
        String footerFormat = "test";
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, format, footerFormat, valueFormatter);

        // WHEN
        String result = customFormatter.formatGeneratedObject(null);

        // THEN
        assertThat(result).isEqualTo("static format");
    }

    @Test
    public void formatGeneratedObject_should_format_given_generatedObject_according_given_format() throws Exception {

        // GIVEN
        String format = "HMGET myKey firstName #firstName# lastName #lastName#";
        ValueFormatter valueFormatter = this.valueFormatter;
        String headerFormat = "MULTI";
        String footerFormat = "EXEC";
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, format, footerFormat, valueFormatter);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("durant"), string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("jean"), string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String result = customFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("HMGET myKey firstName durant lastName jean");
    }

    @Test
    public void formatHeader_should_return_given_format() throws Exception {

        // GIVEN
        String headerFormat = "MULTI";
        String bodyFormat = "";
        String footerFormat = "EXEC";
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, bodyFormat, footerFormat, valueFormatter);
        Entity entity = Nebula.newEntity("test");

        // WHEN
        String result = customFormatter.formatHeader(entity);

        // THEN
        assertThat(result).isEqualTo(headerFormat);
    }

    @Test
    public void formatFooter_should_return_given_format() throws Exception {

        // GIVEN
        String headerFormat = "MULTI";
        String bodyFormat = "";
        String footerFormat = "EXEC";
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, bodyFormat, footerFormat, valueFormatter);
        Entity entity = Nebula.newEntity("test");

        // WHEN
        String result = customFormatter.formatFooter(entity);

        // THEN
        assertThat(result).isEqualTo(footerFormat);
    }
}
