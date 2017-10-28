package com.nebula.formatter.json;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.formatter.ValueFormatter;
import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonFormatterTest {

    @Test
    public void formatHeader_should_return_empty_string() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();

        // WHEN
        String result = jsonFormatter.formatHeader(null);

        // THEN
        assertThat(result).isEmpty();
    }

    @Test
    public void formatGeneratedObject_should_return_empty_string() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();

        // WHEN
        String result = jsonFormatter.formatFooter(null);

        // THEN
        assertThat(result).isEmpty();
    }

    @Test
    public void formatGeneratedObject_should_return_test() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        GeneratedObject generatedObject = new GeneratedObject("text");

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("\"text\"");
    }

    @Test
    public void formatGeneratedObject_should_return_0() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        GeneratedObject generatedObject = new GeneratedObject(BigDecimal.ZERO);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("0");
    }

    @Test
    public void formatGeneratedObject_should_return_1_comma_000_dot_25() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        GeneratedObject generatedObject = new GeneratedObject(new BigDecimal("1000.25"));

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("1,000.25");
    }

    @Test
    public void formatGeneratedObject_should_return_02_slash_01_slash_2017() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        GeneratedObject generatedObject = new GeneratedObject(new DateTime(2017, 2, 1, 0, 0));

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("02/01/2017");
    }

    @Test
    public void formatGeneratedObject_should_return_another_text() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        GeneratedObject generatedObject = new GeneratedObject("another text");

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("\"another text\"");
    }

    @Test
    public void formatGeneratedObject_should_return_json_object_with_one_property() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(properties);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("{field:\"value\"}");
    }

    @Test
    public void formatGeneratedObject_should_return_json_object_with_field_quoted() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatterWithQuotedFields();
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(properties);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("{\"field\":\"value\"}");
    }

    @Test
    public void formatGeneratedObject_should_return_json_object_with_two_property() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("field1", new GeneratedObject("value1"), NebulaGenerationTypes.string().build()));
        properties.add(new GeneratedProperty("field2", new GeneratedObject("value2"), NebulaGenerationTypes.string().build()));
        GeneratedObject generatedObject = new GeneratedObject(properties);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("{field1:\"value1\",field2:\"value2\"}");
    }

    @Test
    public void formatGeneratedObject_should_return_json_object_sub_object() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        List<GeneratedProperty> properties = new ArrayList<>();
        List<GeneratedProperty> subObjectProperties = new ArrayList<>();
        subObjectProperties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        properties.add(new GeneratedProperty("test", new GeneratedObject(subObjectProperties), NebulaGenerationTypes.entity("test").build()));
        GeneratedObject generatedObject = new GeneratedObject(properties);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("{test:{field:\"value\"}}");
    }

    @Test
    public void formatGeneratedObject_should_return_empty_array() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        Object list = new ArrayList<GeneratedObject>();
        GeneratedObject generatedObject = new GeneratedObject(list);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("[]");
    }

    @Test
    public void formatGeneratedObject_should_return_array_with_two_items() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        List<GeneratedObject> list = new ArrayList<>();
        list.add(new GeneratedObject("item 1"));
        list.add(new GeneratedObject("item 2"));
        GeneratedObject generatedObject = new GeneratedObject(list);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("[\"item 1\",\"item 2\"]");
    }

    @Test
    public void formatGeneratedObject_should_return_array_with_one_object() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatter();
        List<GeneratedObject> list = new ArrayList<>();
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        list.add(new GeneratedObject(properties));
        GeneratedObject generatedObject = new GeneratedObject(list);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("[{field:\"value\"}]");
    }

    @Test
    public void formatGeneratedObject_should_return_json_object_with_pretty_format() throws Exception {

        // GIVEN
        JsonFormatter jsonFormatter = newJsonFormatterWithPrettyFormat();
        List<GeneratedProperty> properties = new ArrayList<>();
        List<GeneratedProperty> subObjectProperties = new ArrayList<>();
        subObjectProperties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        subObjectProperties.add(new GeneratedProperty("field", new GeneratedObject("value"), NebulaGenerationTypes.string().build()));
        properties.add(new GeneratedProperty("test", new GeneratedObject(subObjectProperties), NebulaGenerationTypes.entity("test").build()));
        GeneratedObject generatedObject = new GeneratedObject(properties);

        // WHEN
        String result = jsonFormatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo("{\n" +
                                     "  test: {\n" +
                                     "    field: \"value\",\n" +
                                     "    field: \"value\"\n" +
                                     "  }\n" +
                                     "}");
    }

    private JsonFormatter newJsonFormatterWithPrettyFormat() {
        char numberDecimalSeparator = '.';
        char numberThousandSeparator = ',';
        String dateFormat = "MM/dd/yyyy";
        boolean quotedFields = false;
        boolean prettyFormat = true;
        return new JsonFormatter(prettyFormat, quotedFields, new ValueFormatter(dateFormat, numberDecimalSeparator, numberThousandSeparator));
    }

    private JsonFormatter newJsonFormatter() {
        char numberDecimalSeparator = '.';
        char numberThousandSeparator = ',';
        String dateFormat = "MM/dd/yyyy";
        boolean quotedFields = false;
        boolean prettyFormat = false;
        return new JsonFormatter(prettyFormat, quotedFields, new ValueFormatter(dateFormat, numberDecimalSeparator, numberThousandSeparator));
    }

    private JsonFormatter newJsonFormatterWithQuotedFields() {
        char numberDecimalSeparator = '.';
        char numberThousandSeparator = ',';
        String dateFormat = "MM/dd/yyyy";
        boolean quotedFields = true;
        boolean prettyFormat = false;
        return new JsonFormatter(prettyFormat, quotedFields, new ValueFormatter(dateFormat, numberDecimalSeparator, numberThousandSeparator));
    }
}
