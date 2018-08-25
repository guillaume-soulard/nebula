package com.nebula.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.entity;
import static org.assertj.core.api.Assertions.assertThat;

class RecursiveModelGenerationTest {

    @Test
    @DisplayName("generateEntityObject should not throw StackOverflowException")
    void generateEntityObject_should_not_throw_StackOverflowException() {

        // GIVEN
        Model model = ModelBuilder.newEmptyModel().withSeed("recursive entities").build();
        model.newEntity("entity").addProperty("subEntity", random(), entity("entity"));

        // WHEN
        GeneratedObject result = model.generateEntityObject("entity", 0L);

        // THEN
        assertThat(result).isNotNull();
    }
}
