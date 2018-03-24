package com.nebula.examples.sql;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.generationrule.GenerationRules;

import java.math.BigDecimal;

import static com.nebula.core.types.NebulaTypes.*;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.formatter.NebulaFormatters.sql;
import static com.nebula.core.generationconstraint.NebulaConstraints.amount;
import static com.nebula.core.output.NebulaOutputs.stdout;

class Sql {

    public static void main(String[] args) {
        Model model = ModelBuilder.newModel().withSeed(0L).build();

        Entity users = model.newEntity("users");
        users.addProperty("id", sequence(), number().range().withMin(BigDecimal.ZERO));
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("department", random(), amongItems("Dept 1",
                "Dept 2",
                "Dept 3",
                "Dept 4",
                "Dept 5"));

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(stdout())
                .withFormatter(sql().addPropertyToIgnore("_id"))
                .withEntity(users)
                .addGenerationConstraint(amount(10)));
        model.generate();
    }
}
