package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class NebulaSingleEntityDataFetcher implements DataFetcher<GeneratedObject> {

    private final Model model;
    private final String entityName;

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
