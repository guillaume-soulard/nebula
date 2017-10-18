package com.nebula.examples.simple;

import com.nebula.Model;
import com.nebula.Nebula;
import com.nebula.core.Entity;
import com.nebula.core.NebulaGenerationTypes;
import com.nebula.core.NebulaGenerators;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.output.NebulaOutputs;

import java.math.BigDecimal;

public class SimpleGeneration {

    public static void main(String[] args) {
        Model model = Nebula.newModel();

        Entity entity = Nebula.newEntity("test");
        entity.addProperty("string", NebulaGenerators.sequance().cycle(), NebulaGenerationTypes.number().range().withMin(BigDecimal.ZERO));
        model.addEntity(entity);

        model.addGenerationRule(Nebula.newGenerationRule()
                .withEntity("test")
                .withFormatter(NebulaFormatters.csv())
                .addOutput(NebulaOutputs.stdout())
                .addGenerationConstraint(NebulaConstraints.amount(10)));

        model.generate();
    }
}
