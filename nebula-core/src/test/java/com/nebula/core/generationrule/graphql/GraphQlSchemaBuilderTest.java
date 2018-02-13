package com.nebula.core.generationrule.graphql;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import graphql.GraphQL;
import org.junit.Test;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.types.NebulaTypes.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GraphQlSchemaBuilderTest {

    @Test
    public void buildSchemaFrom_should_not_return_null() throws Exception {

        // GIVEN
        GraphQlSchemaBuilder builder = new GraphQlSchemaBuilder();

        // WHEN
        GraphQL result = builder.buildSchemaFrom(getModel());

        // THEN
        assertThat(result).isNotNull();
    }

    private Model getModel() {
        Model model = new ModelBuilder().build();
        Entity entity = model.newEntity("entity");
        entity.addProperty("string", random() , string());
        entity.addProperty("date", random(), dateTime().range());
        entity.addProperty("number", random(), number().range());
        entity.addProperty("bool", random(), bool());
        entity.addProperty("constant", random(), constant("constant"));
        entity.addProperty("entityList", random(), list().of(random(), entity("subEntity")));
        entity.addProperty("stringList", random(), list().of(random(), string()));
        entity.addProperty("amongItems", random(), amongItems("1", "2", "3"));
        model.addEntity(entity);

        Entity subEntity = model.newEntity("subEntity");
        subEntity.addProperty("string", random() , string());
        subEntity.addProperty("date", random(), dateTime().range());
        subEntity.addProperty("number", random(), number().range());
        subEntity.addProperty("bool", random(), bool());
        subEntity.addProperty("constant", random(), constant("constant"));
        subEntity.addProperty("stringList", random(), list().of(random(), string()));
        subEntity.addProperty("amongItems", random(), amongItems("1", "2", "3"));
        model.addEntity(subEntity);

        return model;
    }
}
