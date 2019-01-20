package com.nebula.core.generators;

import com.nebula.core.GeneratedProperties;
import com.nebula.core.GeneratedProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GeneratedPropertiesTest {

    @Test
    @DisplayName("getPropertyByName should return the given property")
    void test1() {

        // GIVEN
        GeneratedProperty property = new GeneratedProperty("test", null, null);
        GeneratedProperties generatedProperties = new GeneratedProperties(Collections.singletonList(property));

        // WHEN
        Optional<GeneratedProperty> result = generatedProperties.getPropertyByName("test");

        // THEN
        assertThat(result).contains(property);
    }

    @Test
    @DisplayName("getPropertyByName should return empty")
    void test2() {

        // GIVEN
        GeneratedProperty property = new GeneratedProperty("test", null, null);
        GeneratedProperties generatedProperties = new GeneratedProperties(Collections.singletonList(property));

        // WHEN
        Optional<GeneratedProperty> result = generatedProperties.getPropertyByName("unexisting");

        // THEN
        assertThat(result).isEmpty();
    }
}