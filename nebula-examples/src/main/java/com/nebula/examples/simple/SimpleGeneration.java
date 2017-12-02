package com.nebula.examples.simple;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.types.NebulaTypes;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationconstraint.NebulaConstraints;
import com.nebula.core.generationrule.GenerationRules;
import com.nebula.core.output.NebulaOutputs;

import java.math.BigDecimal;

public class SimpleGeneration {

    public static void main(String[] args) {
        Model model = new ModelBuilder().build();

        Entity entity = model.newEntity("test");
        entity.addProperty("string", NebulaGenerators.sequence().cycle(), NebulaTypes.number().range().withMin(BigDecimal.ZERO));
        model.addEntity(entity);

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .withEntity("test")
                .withFormatter(NebulaFormatters.csv())
                .addOutput(NebulaOutputs.stdout())
                .addGenerationConstraint(NebulaConstraints.amount(10)));

        model.generate();
    }
}
