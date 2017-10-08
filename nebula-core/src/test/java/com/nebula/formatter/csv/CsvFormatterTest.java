package com.nebula.formatter.csv;

import com.nebula.Nebula;
import com.nebula.core.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvFormatterTest {

    @Test
    public void formatGeneratedObject_should_return_durant() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = false;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("durant");
    }

    @Test
    public void formatGeneratedObject_should_return_jean_durant() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = false;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    public void formatGeneratedObject_should_return_quotted_values() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = false;
        String quote = "\"";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("\"jean\";\"durant\"");
    }

    @Test
    public void formatHeader_should_return_firstName_lastName() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = true;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        Entity entity = Nebula.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", NebulaGenerators.random(), NebulaGenerationTypes.string());
        entity.addProperty("lastName", NebulaGenerators.random(), NebulaGenerationTypes.string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("firstName;lastName");
    }

    @Test
    public void formatHeader_should_return_col1_col2() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = true;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        Entity entity = Nebula.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("col1", NebulaGenerators.random(), NebulaGenerationTypes.string());
        entity.addProperty("col2", NebulaGenerators.random(), NebulaGenerationTypes.string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("col1;col2");
    }

    @Test
    public void formatHeader_should_return_empty_string() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = false;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        Entity entity = Nebula.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", NebulaGenerators.random(), NebulaGenerationTypes.string());
        entity.addProperty("lastName", NebulaGenerators.random(), NebulaGenerationTypes.string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEmpty();
    }

    @Test
    public void formatHeader_should_return_quoted_strings() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = true;
        String quote = "\"";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        Entity entity = Nebula.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", NebulaGenerators.random(), NebulaGenerationTypes.string());
        entity.addProperty("lastName", NebulaGenerators.random(), NebulaGenerationTypes.string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("\"firstName\";\"lastName\"");
    }

    @Test
    public void formatFooter_should_return_empty_string() throws Exception {

        // GIVEN
        String separator = ";";
        boolean header = false;
        String quote = "";
        CsvFormatter formatter = new CsvFormatter(separator, quote, header);
        Entity entity = Nebula.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", NebulaGenerators.random(), NebulaGenerationTypes.string());
        entity.addProperty("lastName", NebulaGenerators.random(), NebulaGenerationTypes.string());

        // WHEN
        String formattedHeader = formatter.formatFooter(entity);

        // THEN
        assertThat(formattedHeader).isEmpty();
    }
}
