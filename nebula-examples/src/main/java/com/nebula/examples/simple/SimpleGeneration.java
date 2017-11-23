package com.nebula.examples.simple;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.generationrule.GenerationRules;
import com.nebula.output.NebulaOutputs;

import java.math.BigDecimal;

public class SimpleGeneration {

    public static void main(String[] args) {
        Model model = new ModelBuilder().build();

        Entity entity = model.newEntity("test");
        entity.addProperty("string", NebulaGenerators.sequence().cycle(), NebulaGenerationTypes.number().range().withMin(BigDecimal.ZERO));
        model.addEntity(entity);

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .withEntity("test")
                .withFormatter(NebulaFormatters.csv())
                .addOutput(NebulaOutputs.stdout())
                .addGenerationConstraint(NebulaConstraints.amount(10)));

        model.generate();
    }
}
