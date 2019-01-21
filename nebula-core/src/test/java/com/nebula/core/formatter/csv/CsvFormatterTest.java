package com.nebula.core.formatter.csv;

import com.nebula.core.*;
import com.nebula.core.formatter.ValueFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("formatGeneratedObject should return durant")
    void formatGeneratedObject_should_return_durant() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Collections.singletonList("lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("lastName", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("durant");
    }

    @Test
    @DisplayName("formatGeneratedObject should return jean durant")
    void formatGeneratedObject_should_return_jean_durant() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", GeneratedObject.of("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    @DisplayName("formatGeneratedObject should return jean durant if no coluns are specified")
    void formatGeneratedObject_should_return_jean_durant_if_no_coluns_are_specified() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        List<String> columnNames = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, columnNames, valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", GeneratedObject.of("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    @DisplayName("formatGeneratedObject should return quotted values")
    void formatGeneratedObject_should_return_quotted_values() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "\"";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Arrays.asList("firstName", "lastName"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("firstName", GeneratedObject.of("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("lastName", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("\"jean\";\"durant\"");
    }

    @Test
    @DisplayName("formatGeneratedObject should return only value of col1")
    void formatGeneratedObject_should_return_only_value_of_col1() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, Collections.singletonList("col1"), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("col1", GeneratedObject.of("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("col2", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean");
    }

    @Test
    @DisplayName("formatGeneratedObject should return all columns when empty column list is passed")
    void formatGeneratedObject_should_return_all_columns_when_empty_column_list_is_passed() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
        String separator = ";";
        String quote = "";
        List<String> propertiesToExclude = new ArrayList<>();
        CsvFormatter formatter = new CsvFormatter(separator, quote, false, new ArrayList<>(), valueFormatter, propertiesToExclude);
        List<GeneratedProperty> generatedProperties = new ArrayList<>();

        Entity entity = model.newEntity("test", Long.MAX_VALUE);
        entity.addProperty("col1", random(), string());
        entity.addProperty("col2", random(), string());

        generatedProperties.add(new GeneratedProperty("col1", GeneratedObject.of("jean"), string().build(model)));
        generatedProperties.add(new GeneratedProperty("col2", GeneratedObject.of("durant"), string().build(model)));
        GeneratedObject generatedObject = GeneratedObject.of(generatedProperties);
        formatter.formatHeader(entity);

        // WHEN
        String formattedObject = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(formattedObject).isEqualTo("jean;durant");
    }

    @Test
    @DisplayName("formatHeader should return firstName lastName")
    void formatHeader_should_return_firstName_lastName() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
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
    @DisplayName("formatHeader should return col1 col2")
    void formatHeader_should_return_col1_col2() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
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
    @DisplayName("formatHeader should return empty string")
    void formatHeader_should_return_empty_string() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
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
    @DisplayName("formatHeader should return quoted strings")
    void formatHeader_should_return_quoted_strings() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
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
    @DisplayName("formatFooter should return empty string")
    void formatFooter_should_return_empty_string() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().build();
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
