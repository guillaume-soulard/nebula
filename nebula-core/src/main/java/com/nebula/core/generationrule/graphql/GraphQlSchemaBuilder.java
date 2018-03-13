package com.nebula.core.generationrule.graphql;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.Property;
import com.nebula.core.types.JavaType;
import com.nebula.core.types.JavaTypeName;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.schema.*;

import java.util.HashMap;
import java.util.Map;

import static graphql.GraphQL.newGraphQL;
import static graphql.Scalars.GraphQLID;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

public class GraphQlSchemaBuilder {

    private final Map<String, GraphQLObjectType> convertedEntities = new HashMap<>();

    public GraphQL buildSchemaFrom(Model model) {

        convertedEntities.clear();

        GraphQLObjectType.Builder query = newObject()
                .name("GraphQlQueryType");

        for (Entity entity : model.getEntities()) {
            buildGraphQlEntityIfNeeded(model, entity);
        }


        for (Map.Entry<String, GraphQLObjectType> convertedEntity : convertedEntities.entrySet()) {

            query.field(newFieldDefinition()
                    .name("one_" + convertedEntity.getKey())
                    .type(convertedEntity.getValue())
                    .dataFetcher(new NebulaSingleEntityDataFetcher(model, convertedEntity.getKey()))
                    .argument(newArgument()
                            .name("_id")
                            .type(GraphQLID)
                    )
            );

            query.field(newFieldDefinition()
                    .name("many_" + convertedEntity.getKey())
                    .type(list(convertedEntity.getValue()))
                    .dataFetcher(new NebulaEntityListDataFetcher(model, convertedEntity.getKey()))
                    .argument(newArgument()
                            .name("_id")
                            .type(GraphQLID)
                    ).argument(newArgument()
                            .name("offset")
                            .type(GraphQLID)
                    )
            );
        }

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(query)
                .build();

        return newGraphQL(schema)
                .queryExecutionStrategy(new BatchedExecutionStrategy())
                .build();
    }

    private void buildGraphQlEntityIfNeeded(Model model, Entity entity) {

        if (!convertedEntities.containsKey(entity.getName())) {

            GraphQLObjectType.Builder type = newObject()
                    .name(entity.getName());

            addFieldForProperty(model, type, "_id", JavaType.NUMBER);
            for (Property property : entity.getProperties()) {

                addFieldForProperty(model, type, property.getName(), property.getType().getJavaType());
            }

            convertedEntities.put(entity.getName(), type.build());
        }
    }

    private void addFieldForProperty(Model model, GraphQLObjectType.Builder type, String propertyName, JavaType propertyType) {
        GraphQLFieldDefinition.Builder fieldDefinition = newFieldDefinition()
                .name(propertyName);

        GraphQLType fieldType = getType(model, propertyType);

        if (isList(propertyType)) {
            fieldDefinition.type(list(fieldType));
        } else {
            fieldDefinition.type((GraphQLOutputType) fieldType);
        }

        fieldDefinition.dataFetcher(new NebulaFieldDataFetcher(propertyName));

        type.field(fieldDefinition);
    }

    private boolean isList(JavaType javaType) {
        return JavaTypeName.ARRAY.name().equals(javaType.getName());
    }

    private GraphQLType getType(Model model, JavaType javaType) {

        GraphQLType graphQlType;

        if (JavaType.BOOLEAN.equals(javaType)) {
            graphQlType = Scalars.GraphQLBoolean;
        } else if (JavaType.NUMBER.equals(javaType)) {
            graphQlType = Scalars.GraphQLBigDecimal;
        } else if (JavaType.STRING.equals(javaType)
                || JavaType.DATE.equals(javaType)) {
            graphQlType = Scalars.GraphQLString;
        } else if (JavaTypeName.ARRAY.name().equals(javaType.getName())) {
            graphQlType = getType(model, javaType.getSubType());
        } else {
            buildGraphQlEntityIfNeeded(model, model.getEntityByName(javaType.getName()));
            graphQlType = convertedEntities.get(javaType.getName());
        }

        return graphQlType;
    }
}
