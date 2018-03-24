package com.nebula.examples.iot;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;

import java.math.BigDecimal;

import static com.nebula.core.formatter.NebulaFormatters.csv;
import static com.nebula.core.generationconstraint.NebulaConstraints.during;
import static com.nebula.core.generationconstraint.NebulaConstraints.every;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static com.nebula.core.types.NebulaTypes.number;
import static java.util.concurrent.TimeUnit.SECONDS;

class IotDataProducer {

    public static void main(String[] args) {
        Model model = ModelBuilder.newModel().withSeed("IOT data producer").build();

        Entity iot = model.newEntity("iot")
                .addProperty("temperature", random(), number().range()
                        .withMin(BigDecimal.ZERO).withMax(BigDecimal.TEN).withPrecision(2));

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(stdout())
                .withFormatter(csv().addPropertyToIgnore("_id").withoutHeader())
                .withEntity(iot)
                .addGenerationConstraint(during(10, SECONDS))
                .addGenerationConstraint(every(1, SECONDS)));
        model.generate();
    }
}
