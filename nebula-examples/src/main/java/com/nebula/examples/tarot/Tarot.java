package com.nebula.examples.tarot;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generators.NebulaGenerators.*;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static com.nebula.core.types.NebulaTypes.*;

class Tarot {

    public static void main(String[] args) {
        Model model = ModelBuilder.newEmptyModel().withSeed(0L).build();

        Entity tarot = model.newEntity("tarot", 4);
        tarot.addProperty("player", sequence(), amongItems("North", "South", "East", "West"));
        tarot.addProperty("hand", random(), list().of(randomUnique(), entity("card")).withMinSize(18).withMaxSize(18));

        model.newStaticEntity("card")
                .newRecord().addProperty("color", "Spade").addProperty("height", "1").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "2").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "3").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "4").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "5").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "6").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "7").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "8").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "9").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "10").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "Jack").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "Knight").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "Queen").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Spade").addProperty("height", "King").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "1").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "2").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "3").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "4").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "5").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "6").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "7").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "8").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "9").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "10").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "Jack").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "Knight").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "Queen").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Heart").addProperty("height", "King").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "1").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "2").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "3").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "4").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "5").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "6").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "7").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "8").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "9").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "10").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "Jack").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "Knight").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "Queen").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Diamond").addProperty("height", "King").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "1").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "2").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "3").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "4").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "5").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "6").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "7").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "8").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "9").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "10").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "Jack").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "Knight").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "Queen").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Club").addProperty("height", "King").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "1").addProperty("isOudler", true)
                .newRecord().addProperty("color", "Trump").addProperty("height", "2").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "3").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "4").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "5").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "6").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "7").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "8").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "9").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "10").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "11").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "12").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "13").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "14").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "15").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "16").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "17").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "18").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "19").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "20").addProperty("isOudler", false)
                .newRecord().addProperty("color", "Trump").addProperty("height", "21").addProperty("isOudler", true)
                .newRecord().addProperty("color", "Trump").addProperty("height", "Fool").addProperty("isOudler", true)
                .buildEntity();

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(stdout())
                .withFormatter(json().pretty())
                .withEntity(tarot));
        model.generate();
    }
}
