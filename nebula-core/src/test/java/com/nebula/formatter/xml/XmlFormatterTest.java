package com.nebula.formatter.xml;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.types.Type;
import com.nebula.formatter.ValueFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XmlFormatterTest {

    @Test
    public void formatGeneratedObject_should_format_simple_generated_object() throws Exception {

        // GIVEN
        XmlFormatter formatter = new XmlFormatter(new ValueFormatter("dd/MM/yyyy", '.', ','));
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("id", new GeneratedObject(1), mock(Type.class)));
        properties.add(new GeneratedProperty("firstName", new GeneratedObject("Durant"), mock(Type.class)));
        properties.add(new GeneratedProperty("lastName", new GeneratedObject("Jean"), mock(Type.class)));
        GeneratedObject generatedObject = new GeneratedObject(properties);
        Entity entity = mock(Entity.class);
        when(entity.getName()).thenReturn("user");
        formatter.formatHeader(entity);

        // WHEN
        String result = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo(
                "<user>\r\n" +
                "  <id>1</id>\r\n" +
                "  <firstName>Durant</firstName>\r\n" +
                "  <lastName>Jean</lastName>\r\n" +
                "</user>");
    }

    @Test
    public void formatGeneratedObject_should_format_complex_generated_object() throws Exception {

        // GIVEN
        XmlFormatter formatter = new XmlFormatter(new ValueFormatter("dd/MM/yyyy", '.', ','));
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("id", new GeneratedObject(1), mock(Type.class)));
        properties.add(new GeneratedProperty("firstName", new GeneratedObject("Durant"), mock(Type.class)));
        properties.add(new GeneratedProperty("lastName", new GeneratedObject("Jean"), mock(Type.class)));
        properties.add(new GeneratedProperty("address", generateAddress(), mock(Type.class)));
        GeneratedObject generatedObject = new GeneratedObject(properties);
        Entity entity = mock(Entity.class);
        when(entity.getName()).thenReturn("user");
        formatter.formatHeader(entity);

        // WHEN
        String result = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo(
                "<user>\r\n" +
                "  <id>1</id>\r\n" +
                "  <firstName>Durant</firstName>\r\n" +
                "  <lastName>Jean</lastName>\r\n" +
                "  <address>\r\n" +
                "    <street>1 rue des champs elysees</street>\r\n"+
                "    <city>Paris</city>\r\n"+
                "  </address>\r\n" +
                "</user>");
    }

    @Test
    public void formatGeneratedObject_should_format_complex_generated_object_with_array() throws Exception {

        // GIVEN
        XmlFormatter formatter = new XmlFormatter(new ValueFormatter("dd/MM/yyyy", '.', ','));
        List<GeneratedProperty> properties = new ArrayList<>();
        properties.add(new GeneratedProperty("id", new GeneratedObject(1), mock(Type.class)));
        properties.add(new GeneratedProperty("firstName", new GeneratedObject("Durant"), mock(Type.class)));
        properties.add(new GeneratedProperty("lastName", new GeneratedObject("Jean"), mock(Type.class)));
        properties.add(new GeneratedProperty("addresses", new GeneratedObject(Arrays.asList(generateAddress(), generateAddress())), mock(Type.class)));
        GeneratedObject generatedObject = new GeneratedObject(properties);
        Entity entity = mock(Entity.class);
        when(entity.getName()).thenReturn("user");
        formatter.formatHeader(entity);

        // WHEN
        String result = formatter.formatGeneratedObject(generatedObject);

        // THEN
        assertThat(result).isEqualTo(
                "<user>\r\n" +
                        "  <id>1</id>\r\n" +
                        "  <firstName>Durant</firstName>\r\n" +
                        "  <lastName>Jean</lastName>\r\n" +
                        "  <addresses>\r\n" +
                        "    <item>\r\n" +
                        "      <street>1 rue des champs elysees</street>\r\n" +
                        "      <city>Paris</city>\r\n" +
                        "    </item>\r\n" +
                        "    <item>\r\n" +
                        "      <street>1 rue des champs elysees</street>\r\n" +
                        "      <city>Paris</city>\r\n" +
                        "    </item>\r\n" +
                        "  </addresses>\r\n" +
                        "</user>");
    }

    private GeneratedObject generateAddress() {
        List<GeneratedProperty> addressProperties = new ArrayList<>();
        addressProperties.add(new GeneratedProperty("street", new GeneratedObject("1 rue des champs elysees"), mock(Type.class)));
        addressProperties.add(new GeneratedProperty("city", new GeneratedObject("Paris"), mock(Type.class)));
        return new GeneratedObject(addressProperties);
    }
}