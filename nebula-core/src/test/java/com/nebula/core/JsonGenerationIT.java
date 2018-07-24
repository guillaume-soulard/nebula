package com.nebula.core;

import com.nebula.core.generationconstraint.NebulaConstraints;
import com.nebula.core.generationrule.GenerationRuleBuilder;
import com.nebula.core.generationrule.GenerationRules;
import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.output.NebulaOutputs.file;
import static com.nebula.core.types.NebulaTypes.dateTime;
import static com.nebula.core.types.NebulaTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

class JsonGenerationIT {

    @Test
    @ExtendWith(TemporaryFolderExtension.class)
    void generate_should_generate_a_correct_csv_file(TemporaryFolder temporaryFolder) {

        // GIVEN
        Model model = ModelBuilder.newModel().withSeed(1L).withDateFormat("dd/MM/yyyy").build();
        Entity users = model.newEntity("users", 100L);
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        DateTime minDayOfBirth = new DateTime(1950, 1, 1, 0, 0);
        DateTime maxDayOfBirth = new DateTime(2000, 1, 1, 0, 0);
        users.addProperty("dayOfBirth", random(), dateTime().range().withMin(minDayOfBirth).withMax(maxDayOfBirth));

        File fileToGenerate = new File(temporaryFolder.getRoot(), "users.json");

        GenerationRuleBuilder generationRule = GenerationRules.newOneShootGenerationRule()
                .withEntity(users)
                .withFormatter(json().pretty().quotedFields().addPropertyToIgnore("_id"))
                .addOutput(file(fileToGenerate.getPath()))
                .addGenerationConstraint(NebulaConstraints.amount(10));

        model.addGenerationRule(generationRule);

        // WHEN
        model.generate();

        // THEN
        assertThat(fileToGenerate).hasContent("\n" +
                "{\n" +
                "  \"firstName\": \"Zfjjm\",\n" +
                "  \"lastName\": \"Iouh\",\n" +
                "  \"dayOfBirth\": 22/03/1966\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Ftwsy\",\n" +
                "  \"lastName\": \"Ocjrj\",\n" +
                "  \"dayOfBirth\": 08/09/1990\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Nfftlu\",\n" +
                "  \"lastName\": \"Wrfhvvv\",\n" +
                "  \"dayOfBirth\": 23/08/1996\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Ttuc\",\n" +
                "  \"lastName\": \"Cftojvj\",\n" +
                "  \"dayOfBirth\": 28/06/1951\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Aupwkvnlk\",\n" +
                "  \"lastName\": \"Irbqwqsdz\",\n" +
                "  \"dayOfBirth\": 12/04/1986\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Gicd\",\n" +
                "  \"lastName\": \"Odqxhsh\",\n" +
                "  \"dayOfBirth\": 29/11/1981\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Mvlgltk\",\n" +
                "  \"lastName\": \"Wsknwaazc\",\n" +
                "  \"dayOfBirth\": 13/09/1966\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Sianxvyif\",\n" +
                "  \"lastName\": \"Cgzu\",\n" +
                "  \"dayOfBirth\": 25/10/1961\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Aywd\",\n" +
                "  \"lastName\": \"Isix\",\n" +
                "  \"dayOfBirth\": 09/08/1996\n" +
                "}\n" +
                "{\n" +
                "  \"firstName\": \"Gjjmw\",\n" +
                "  \"lastName\": \"Ogxeiymwx\",\n" +
                "  \"dayOfBirth\": 25/01/1971\n" +
                "}\n");
    }
}
