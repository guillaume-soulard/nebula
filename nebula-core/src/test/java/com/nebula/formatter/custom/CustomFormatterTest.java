package com.nebula.formatter.custom;

import com.nebula.Model;
import com.nebula.Nebula;
import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaGenerators;
import com.nebula.formatter.ValueFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.nebula.Nebula.newEntity;
import static com.nebula.Nebula.newModel;
import static com.nebula.core.NebulaGenerationTypes.*;
import static com.nebula.core.NebulaGenerators.random;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomFormatterTest {

    private ValueFormatter valueFormatter = new ValueFormatter("MM/dd/yyyy", '.', ',');;

    @Test
    public void formatGeneratedObject_should_return_given_format() throws Exception {

        // GIVEN
        String format = "static format";
        String headerFormat = "test";
        String footerFormat = "test";
        List<String> propertiesToExclude = new ArrayList<>();
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, format, footerFormat, valueFormatter, propertiesToExclude);

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
        List<String> propertiesToExclude = new ArrayList<>();
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, format, footerFormat, valueFormatter, propertiesToExclude);
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
    public void formatGeneratedObject_should_format_sub_object() throws Exception {

        // GIVEN
        String format = "HMGET myKey firstName #subEntity.subProperty#";
        ValueFormatter valueFormatter = this.valueFormatter;
        String headerFormat = "MULTI";
        String footerFormat = "EXEC";
        List<String> propertiesToExclude = new ArrayList<>();
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, format, footerFormat, valueFormatter, propertiesToExclude);
        Model model = newModel();
        Entity entity = newEntity("entity");
        Entity subEntity = newEntity("subEntity");
        subEntity.addProperty("subProperty", random(), constant("durant"));
        model.addEntity(subEntity);
        entity.addProperty("subEntity", random(), entity("subEntity"));
        model.addEntity(entity);
        GeneratedObject generatedObject = model.generateEntityObject(entity, 0L, 1L);

        // WHEN
        String result = customFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("HMGET myKey firstName durant");
    }

    @Test
    public void formatHeader_should_return_given_format() throws Exception {

        // GIVEN
        String headerFormat = "MULTI";
        String bodyFormat = "";
        String footerFormat = "EXEC";
        List<String> propertiesToExclude = new ArrayList<>();
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, bodyFormat, footerFormat, valueFormatter, propertiesToExclude);
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
        List<String> propertiesToExclude = new ArrayList<>();
        CustomFormatter customFormatter = new CustomFormatter(headerFormat, bodyFormat, footerFormat, valueFormatter, propertiesToExclude);
        Entity entity = Nebula.newEntity("test");

        // WHEN
        String result = customFormatter.formatFooter(entity);

        // THEN
        assertThat(result).isEqualTo(footerFormat);
    }
}
