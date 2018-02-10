package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NebulaSingleEntityDataFetcher implements DataFetcher<GeneratedObject> {

    private Model model;
    private String entityName;

    NebulaSingleEntityDataFetcher(Model model, String entityName) {
        this.model = model;
        this.entityName = entityName;
    }

    @Override
    public GeneratedObject get(DataFetchingEnvironment environment) {

        if (environment.getArguments().containsKey("_id")
                && environment.getArguments().size() == 1) {

            return model.generateEntityObject(entityName, Long.valueOf(environment.getArgument("_id")));
        }

        return null;
    }
}
