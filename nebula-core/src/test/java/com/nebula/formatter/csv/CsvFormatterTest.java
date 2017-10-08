package com.nebula.formatter.csv;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaGenerationTypes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvFormatterTest {

    @Test
    public void new_CsvFormatter_should_have_default_properties() throws Exception {

        // GIVEN
        CsvFormatter formatter;

        // WHEN
        formatter = new CsvFormatter();

        // THEN
        assertThat(formatter).hasFieldOrPropertyWithValue("separator", ",")
            .hasFieldOrPropertyWithValue("header", true)
            .hasFieldOrPropertyWithValue("quote", "");
    }

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
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedHeader = formatter.formatHeader(generatedObject);

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
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("col1", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("col2", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedHeader = formatter.formatHeader(generatedObject);

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
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedHeader = formatter.formatHeader(generatedObject);

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
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedHeader = formatter.formatHeader(generatedObject);

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
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), NebulaGenerationTypes.string().build()));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedHeader = formatter.formatFooter(generatedObject);

        // THEN
        assertThat(formattedHeader).isEmpty();
    }
}
