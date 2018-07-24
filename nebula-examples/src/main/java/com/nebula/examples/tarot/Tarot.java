package com.nebula.examples.tarot;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.randomUnique;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static com.nebula.core.types.NebulaTypes.*;

class Tarot {

    public static void main(String[] args) {
        Model model = ModelBuilder.newModel().withSeed(0L).build();

        List<String> colors = new ArrayList<>();
        List<String> heights = new ArrayList<>();
        List<Boolean> isOudler = new ArrayList<>();

        buildStaticValues(colors, heights, isOudler);

        Entity tarot = model.newEntity("tarot", 4);
        tarot.addProperty("player", sequence(), amongItems("North", "South", "East", "West"));
        tarot.addProperty("hand", random(), list().of(randomUnique(), entity("card")).withMinSize(18).withMaxSize(18));

        Entity cards = model.newEntity("card", colors.size());
        cards.addProperty("color", sequence(), amongItems(colors.toArray(new String[] {})));
        cards.addProperty("height", sequence(), amongItems(heights.toArray(new String[] {})));
        cards.addProperty("isOudler", sequence(), amongItems(isOudler.toArray(new Boolean[] {})));

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(stdout())
                .withFormatter(json().pretty())
                .withEntity(tarot));
        model.generate();
    }

    private static void buildStaticValues(List<String> colors, List<String> heights, List<Boolean> isOudler) {
        Stream.of("Spade", "Heart", "Diamond", "Club").forEach(color -> {
            IntStream.rangeClosed(1, 10).forEach(height -> {
                    colors.add(color);
                    heights.add(String.valueOf(height));
                    isOudler.add(false);
                });

                Stream.of("Jack", "Knight", "Queen", "King").forEach(height -> {
                    colors.add(color);
                    heights.add(String.valueOf(height));
                    isOudler.add(false);
                });
            }
        );

        IntStream.rangeClosed(1, 21).forEach(trumpHeight -> {

            if (trumpHeight == 1L || trumpHeight == 21L) {
                isOudler.add(true);
            } else {
                isOudler.add(false);
            }

            colors.add("Trump");
            heights.add(String.valueOf(trumpHeight));
        });

        colors.add("Trump");
        heights.add("Fool");
        isOudler.add(true);
    }
}
