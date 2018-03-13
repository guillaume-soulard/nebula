package com.nebula.core.generationrule.graphql;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import graphql.GraphQL;
import org.junit.Test;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GraphQlSchemaBuilderTest {

    @Test
    public void buildSchemaFrom_should_not_return_null() {

        // GIVEN
        GraphQlSchemaBuilder builder = new GraphQlSchemaBuilder();

        // WHEN
        GraphQL result = builder.buildSchemaFrom(getModel());

        // THEN
        assertThat(result).isNotNull();
    }

    private Model getModel() {
        Model model = ModelBuilder.newModel().build();
        model.newEntity("entity")
                .addProperty("string", random(), string())
                .addProperty("date", random(), dateTime().range())
                .addProperty("number", random(), number().range())
                .addProperty("bool", random(), bool())
                .addProperty("constant", constant("constant"))
                .addProperty("entityList", random(), list().of(random(), entity("subEntity")))
                .addProperty("stringList", random(), list().of(random(), string()))
                .addProperty("amongItems", random(), amongItems("1", "2", "3"));

        model.newEntity("subEntity")
            .addProperty("string", random() , string())
            .addProperty("date", random(), dateTime().range())
            .addProperty("number", random(), number().range())
            .addProperty("bool", random(), bool())
            .addProperty("constant", constant("constant"))
            .addProperty("stringList", random(), list().of(random(), string()))
            .addProperty("amongItems", random(), amongItems("1", "2", "3"));

        return model;
    }
}
