package com.nebula.examples.multiplefiles;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationrule.GenerationRules;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.output.NebulaOutputs;
import com.nebula.core.types.NebulaTypes;

class MultipleFilesUsers {

    public static void main(String[] args) {

        System.out.println("Generate 10000 Users");

        int numberOfUsers = 10000;
        int numberOfUsersPerFiles = 1000;

        Model model = ModelBuilder.newEmptyModel()
                .withSeed("a seed")
                .withDateFormat("dd/MM/yyyy")
                .build();

        Entity users = model.newEntity("users", numberOfUsers);
        users.addProperty("firstName", NebulaGenerators.random(), NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("lastName", NebulaGenerators.random(), NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("dayOfBirth", NebulaGenerators.random(), NebulaTypes.dateTime().range().withMin("01/01/1950").withMax("01/01/2000"));

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .withEntity(users)
                .withFormatter(NebulaFormatters.csv().withSeparator(";").withColumns("lastName", "firstName", "dayOfBirth"))
                .addOutput(NebulaOutputs.multipleFiles(System.getProperty("user.dir"))
                        .withMaxObjectPerFiles(numberOfUsersPerFiles)
                        .withFileNameFunction(slicedFileIndexContext -> "users-from-" + ((slicedFileIndexContext.getFileIndex()) * numberOfUsersPerFiles) + "-to-" + ((slicedFileIndexContext.getFileIndex() + 1) * numberOfUsersPerFiles) + ".csv")));

        model.generate();

        System.out.println("Done");
    }
}
