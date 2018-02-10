package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.math.BigDecimal;

public class NebulaFieldDataFetcher implements DataFetcher<Object> {

    private String propertyName;

    NebulaFieldDataFetcher(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public Object get(DataFetchingEnvironment environment) {
        GeneratedObject value = ((GeneratedObject) environment.getSource()).getGeneratedPropertyValue(propertyName);
        return value.getObject();
    }
}
