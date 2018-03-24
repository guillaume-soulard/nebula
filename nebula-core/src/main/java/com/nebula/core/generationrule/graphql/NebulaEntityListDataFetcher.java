package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import com.nebula.core.Model;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.List;

class NebulaEntityListDataFetcher implements DataFetcher<List<GeneratedObject>> {
    private final Model model;
    private final String entityName;

    NebulaEntityListDataFetcher(Model model, String entityName) {
        this.model = model;
        this.entityName = entityName;
    }

    @Override
    public List<GeneratedObject> get(DataFetchingEnvironment environment) {

        if (environment.getArguments().containsKey("_id")
                && environment.getArguments().containsKey("offset")
                && environment.getArguments().size() == 2) {

            Long fromIndex = Long.valueOf(environment.getArgument("_id"));
            Long offset = Long.valueOf(environment.getArgument("offset"));

            List<GeneratedObject> objects = new ArrayList<>();

            for (long index = fromIndex; index < fromIndex + offset; index++) {
                objects.add(model.generateEntityObject(entityName, index));
            }

            return objects;
        }

        return null;
    }
}
