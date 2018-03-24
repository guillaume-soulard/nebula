package com.nebula.core.formatter;

import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.types.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AbstractFormatterTest {

    @Test
    public void excludeFieldsOn_should_return_all_given_properties() {

        // GIVEN
        List<String> excludedFields = new ArrayList<>();
        AbstractFormatter abstractFormatter = newFormatter(excludedFields);
        GeneratedObject generatedObject = new GeneratedObject(getProperties("_id"));

        // WHEN
        GeneratedObject result = abstractFormatter.excludeFieldsOn(generatedObject);

        // THEN
        assertThat(result.getGeneratedProperties()).hasSameElementsAs(generatedObject.getGeneratedProperties());
    }

    @Test
    public void excludeFieldsOn_should_return_no_properties() {

        // GIVEN
        List<String> excludedFields = Collections.singletonList("_id");
        AbstractFormatter abstractFormatter = newFormatter(excludedFields);
        GeneratedObject generatedObject = new GeneratedObject(getProperties("_id"));

        // WHEN
        GeneratedObject result = abstractFormatter.excludeFieldsOn(generatedObject);

        // THEN
        assertThat(result.getGeneratedProperties()).isEmpty();
    }

    @Test
    public void excludeFieldsOn_should_return_one_property() {

        // GIVEN
        List<String> excludedFields = Collections.singletonList("_id");
        AbstractFormatter abstractFormatter = newFormatter(excludedFields);
        GeneratedObject generatedObject = new GeneratedObject(getProperties("_id", "field"));

        // WHEN
        GeneratedObject result = abstractFormatter.excludeFieldsOn(generatedObject);

        // THEN
        assertThat(result.getGeneratedProperties()).hasSize(1).extracting("propertyName").containsOnly("field");
    }

    private List<GeneratedProperty> getProperties(String... propertyNames) {
        List<GeneratedProperty> properties = new ArrayList<>();

        for (String propertyName : propertyNames) {
            properties.add(new GeneratedProperty(propertyName, mock(GeneratedObject.class), mock(Type.class)));
        }

        return properties;
    }

    private AbstractFormatter newFormatter(List<String> excludedFields) {
        return new AbstractFormatter(null, excludedFields) {
            @Override
            public String formatHeader(Entity entity) {
                return null;
            }

            @Override
            public String formatGeneratedObject(GeneratedObject generatedObject) {
                return null;
            }

            @Override
            public String formatFooter(Entity entity) {
                return null;
            }
        };
    }
}