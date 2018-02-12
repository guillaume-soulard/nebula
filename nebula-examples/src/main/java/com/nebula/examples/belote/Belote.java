package com.nebula.examples.belote;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationconstraint.NebulaConstraints;
import com.nebula.core.generationrule.GenerationRules;
import com.nebula.core.generators.NebulaGenerators;
import com.nebula.core.output.NebulaOutputs;
import com.nebula.core.types.NebulaTypes;

import static com.nebula.core.formatter.NebulaFormatters.*;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.randomUnique;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.types.NebulaTypes.*;

public class Belote {

    public static void main(String[] args) {

        System.out.println("Generate 1000000 hands in belote game with 4 players");

        Model model = new ModelBuilder()
                .withSeed("belote")
                .build();

        Entity belote = model.newEntity("belote", Long.MAX_VALUE);
        belote.addProperty("player", sequence().cycle(), picker("North")
                .addItem("South")
                .addItem("East")
                .addItem("West"));

        belote.addProperty("hand", random(), list().of(randomUnique(), entity("card")).withMinSize(8).withMaxSize(8));
        model.addEntity(belote);

        Entity card = model.newEntity("card", 32L);

        card.addProperty("color", sequence().cycle(), picker("Spade")
                .addItem("Heart")
                .addItem("Diamond")
                .addItem("Club"));
        card.addProperty("height", randomUnique(), picker("Ace")
                .addItem("10")
                .addItem("King")
                .addItem("Queen")
                .addItem("Jack")
                .addItem("9")
                .addItem("8")
                .addItem("7"));
        model.addEntity(card);


        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .withEntity(belote)
                .withFormatter(json().pretty())
                .addOutput(NebulaOutputs.stdout())
                .addGenerationConstraint(NebulaConstraints.amount(10)));

        model.generate();

        System.out.println("Done");
    }
}
