package com.nebula.core;

import com.nebula.core.generationconstraint.NebulaConstraints;
import com.nebula.core.generationrule.GenerationRuleBuilder;
import com.nebula.core.generationrule.GenerationRules;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static com.nebula.core.formatter.NebulaFormatters.csv;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.output.NebulaOutputs.file;
import static com.nebula.core.types.NebulaTypes.dateTime;
import static com.nebula.core.types.NebulaTypes.string;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvGenerationIT {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        temporaryFolder.create();
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }

    @Test
    public void generate_should_generate_a_correct_csv_file() {

        // GIVEN
        Model model = ModelBuilder.newModel().withSeed(1L).withDateFormat("dd/MM/yyyy").build();
        DateTime minDayOfBirth = new DateTime(1950, 1, 1, 0, 0);
        DateTime maxDayOfBirth = new DateTime(2000, 1, 1, 0, 0);
        Entity users = model.newEntity("users", 100L)
                .addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"))
                .addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{3,25}"))
                .addProperty("dayOfBirth", random(), dateTime().range().withMin(minDayOfBirth).withMax(maxDayOfBirth));

        File fileToGenerate = new File(temporaryFolder.getRoot(), "users.csv");

        GenerationRuleBuilder generationRule = GenerationRules.newOneShootGenerationRule()
                .withEntity(users)
                .withFormatter(csv().withSeparator(";").withColumns("lastName", "firstName", "dayOfBirth"))
                .addOutput(file(fileToGenerate.getPath()))
                .addGenerationConstraint(NebulaConstraints.amount(10));

        model.addGenerationRule(generationRule);

        // WHEN

        model.generate();

        // THEN
        assertThat(fileToGenerate).hasContent(
                "lastName;firstName;dayOfBirth\n" +
                "Iouh;Zfjjm;22/03/1966\n" +
                "Ocjrj;Ftwsy;08/09/1990\n" +
                "Wrfhvvv;Nfftlu;23/08/1996\n" +
                "Cftojvj;Ttuc;28/06/1951\n" +
                "Irbqwqsdz;Aupwkvnlk;12/04/1986\n" +
                "Odqxhsh;Gicd;29/11/1981\n" +
                "Wsknwaazc;Mvlgltk;13/09/1966\n" +
                "Cgzu;Sianxvyif;25/10/1961\n" +
                "Isix;Aywd;09/08/1996\n" +
                "Ogxeiymwx;Gjjmw;25/01/1971\n" +
                "");
    }
}
