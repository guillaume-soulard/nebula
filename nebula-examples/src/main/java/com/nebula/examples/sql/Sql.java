package com.nebula.examples.sql;

import com.nebula.Model;
import com.nebula.Nebula;
import com.nebula.core.Entity;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.generationrule.GenerationRules;
import com.nebula.output.NebulaOutputs;
import org.apache.http.entity.ContentType;

import java.math.BigDecimal;

import static com.nebula.Nebula.newModel;
import static com.nebula.core.NebulaGenerationTypes.*;
import static com.nebula.core.NebulaGenerationTypes.picker;
import static com.nebula.core.NebulaGenerators.random;
import static com.nebula.core.NebulaGenerators.sequence;
import static com.nebula.formatter.NebulaFormatters.json;
import static com.nebula.formatter.NebulaFormatters.sql;
import static com.nebula.generationconstraint.NebulaConstraints.amount;
import static com.nebula.output.NebulaOutputs.stdout;

public class Sql {

    public static void main(String[] args) {
        Model model = newModel();
        model.setSeed(0L);

        Entity users = model.newEntity("users");
        users.addProperty("id", sequence(), number().range().withMin(BigDecimal.ZERO));
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("department", random(), picker("Dept 1")
                .addItem("Dept 2")
                .addItem("Dept 3")
                .addItem("Dept 4")
                .addItem("Dept 5"));

        model.addEntity(users);

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(stdout())
                .withFormatter(sql().addPropertyToIgnore("_id"))
                .withEntity(users)
                .addGenerationConstraint(amount(10)));
        model.generate();
    }
}
