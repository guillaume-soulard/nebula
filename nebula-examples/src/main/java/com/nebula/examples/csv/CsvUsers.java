package com.nebula.examples.csv;

import com.nebula.Model;
import com.nebula.Nebula;
import com.nebula.core.Entity;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.generationrule.GenerationRules;
import com.nebula.output.NebulaOutputs;
import org.joda.time.DateTime;

import static com.nebula.Nebula.*;

public class CsvUsers {

    private static DateTime MIN_BIRTH_DATE = new DateTime(1950, 1, 1, 0, 0);
    private static DateTime MAX_BIRTH_DATE = new DateTime(2000, 1, 1, 0, 0);

    public static void main(String[] args) {

        System.out.println("Generate 10 Users");

        Model model = Nebula.newModel();
        model.setSeed("a seed");
        model.setDateFormat("dd/MM/yyyy");

        Entity users = model.newEntity("users", Long.MAX_VALUE);
        users.addProperty("firstName", NebulaGenerators.random(), NebulaGenerationTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("lastName", NebulaGenerators.random(), NebulaGenerationTypes.string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("dayOfBirth", NebulaGenerators.random(), NebulaGenerationTypes.dateTime().range().withMin(MIN_BIRTH_DATE).withMax(MAX_BIRTH_DATE));
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
