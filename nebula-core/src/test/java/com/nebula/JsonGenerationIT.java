package com.nebula;

import com.nebula.core.Entity;
import com.nebula.generationconstraint.NebulaConstraints;
import com.nebula.generationrule.GenerationRuleBuilder;
import com.nebula.generationrule.GenerationRules;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static com.nebula.core.NebulaGenerationTypes.dateTime;
import static com.nebula.core.NebulaGenerationTypes.string;
import static com.nebula.core.NebulaGenerators.random;
import static com.nebula.formatter.NebulaFormatters.json;
import static com.nebula.output.NebulaOutputs.file;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonGenerationIT {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        temporaryFolder.create();
    }

    @After
    public void tearDown() throws Exception {
        temporaryFolder.delete();
    }

    @Test
    public void generate_should_generate_a_correct_csv_file() throws Exception {

        // GIVEN
        Model model = new ModelBuilder().withSeed(1L).withDateFormat("dd/MM/yyyy").build();
        Entity users = model.newEntity("users", 100l);
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"));
        DateTime minDayOfBirth = new DateTime(1950, 1, 1, 0, 0);
        DateTime maxDayOfBirth = new DateTime(2000, 1, 1, 0, 0);
        users.addProperty("dayOfBirth", random(), dateTime().range().withMin(minDayOfBirth).withMax(maxDayOfBirth));
        model.addEntity(users);

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
