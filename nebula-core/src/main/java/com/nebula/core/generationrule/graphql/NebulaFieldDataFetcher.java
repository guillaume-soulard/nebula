package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class NebulaFieldDataFetcher implements DataFetcher<Object> {

    private final String propertyName;

    NebulaFieldDataFetcher(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        GeneratedObject value = ((GeneratedObject) environment.getSource()).getGeneratedPropertyValue(propertyName);
        return value.getObject();
    }
}
