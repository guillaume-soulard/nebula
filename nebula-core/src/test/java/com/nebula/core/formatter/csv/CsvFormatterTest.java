package com.nebula.core.formatter.csv;

import com.nebula.core.*;
import com.nebula.core.formatter.ValueFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

class CsvFormatterTest {

    private ValueFormatter valueFormatter;

    @BeforeEach
    void setUp() {
        valueFormatter = new ValueFormatter("dd/MM/yyyy", '.', ',');
    }

    @Test
    void formatGeneratedObject_should_return_durant() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Collections.singletonList("lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("durant");
    }

    @Test
    void formatGeneratedObject_should_return_jean_durant() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    void formatGeneratedObject_should_return_jean_durant_if_no_coluns_are_specified() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        List<String> columnNames = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, columnNames, valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    void formatGeneratedObject_should_return_quotted_values() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "\"";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", new GeneratedObject("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("\"jean\";\"durant\"");
    }

    @Test
    void formatGeneratedObject_should_return_only_value_of_col1() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Collections.singletonList("col1"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("col1", new GeneratedObject("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("col2", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean");
    }

    @Test
    void formatGeneratedObject_should_return_all_columns_when_empty_column_list_is_passed() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, new ArrayList<>(), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();

        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("col1", random(), string());
        entity.addProperty("col2", random(), string());

        generatedProperties.add(new GeneratedProperty("col1", new GeneratedObject("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("col2", new GeneratedObject("durant"), string().build(model)));
        GeneratedObject generatedObject = new GeneratedObject(generatedProperties);
        formatter.formatHeader(entity);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    void formatHeader_should_return_firstName_lastName() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, true, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", random(), string());
        entity.addProperty("lastName", random(), string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("firstName;lastName");
    }

    @Test
    void formatHeader_should_return_col1_col2() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, true, Arrays.asList("col1", "col2"), valueFormatter, propertiesToExclude);
        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("col1", random(), string());
        entity.addProperty("col2", random(), string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("col1;col2");
    }

    @Test
    void formatHeader_should_return_empty_string() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", random(), string());
        entity.addProperty("lastName", random(), string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEmpty();
    }

    @Test
    void formatHeader_should_return_quoted_strings() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "\"";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, true, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", random(), string());
        entity.addProperty("lastName", random(), string());

        // WHEN
        String formattedHeader = formatter.formatHeader(entity);

        // THEN
        assertThat(formattedHeader).isEqualTo("\"firstName\";\"lastName\"");
    }

    @Test
    void formatFooter_should_return_empty_string() {

        // GIVEN
        Model model = ModelBuilder.newModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("firstName", random(), string());
        entity.addProperty("lastName", random(), string());

        // WHEN
        String formattedHeader = formatter.formatFooter(entity);

        // THEN
        assertThat(formattedHeader).isEmpty();
    }
}
