package com.nebula.examples.csv;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.generationrule.GenerationRules;
import com.nebula.output.NebulaOutputs;

public class CsvUsers {

    public static void main(String[] args) {

        System.out.println("Generate 10 Users");

        Model model = new ModelBuilder()
                .withSeed("a seed")
                .withDateFormat("dd/MM/yyyy")
                .build();

        Entity users = model.newEntity("users", Long.MAX_VALUE);
        users.addProperty("firstName", NebulaGenerators.random(), NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("lastName", NebulaGenerators.random(), NebulaTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("dayOfBirth", NebulaGenerators.random(), NebulaTypes.dateTime().range().withMin("01/01/1950").withMax("01/01/2000"));
        model.addEntity(users);

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .withEntity(users)
                .withFormatter(NebulaFormatters.csv().withSeparator(";").withColumns("lastName", "firstName", "dayOfBirth"))
                .addOutput(NebulaOutputs.stdout())
                .addGenerationConstraint(NebulaConstraints.amount(10)));

        model.generate();

        System.out.println("Done");
    }
}
