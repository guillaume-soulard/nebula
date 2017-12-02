package com.nebula.core.output.jdbc;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.nebula.core.types.NebulaTypes.number;
import static com.nebula.core.types.NebulaTypes.string;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.formatter.NebulaFormatters.sql;
import static com.nebula.core.generationrule.GenerationRules.newOneShootGenerationRule;
import static com.nebula.core.output.NebulaOutputs.jdbc;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static org.assertj.core.api.Assertions.assertThat;

public class JdbcOutputIT {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/create-db.sql")
                .addScript("db/insert-data.sql")
                .build();
        this.jdbcTemplate = new JdbcTemplate(db);
    }

    @Test
    public void generate_should_generate_10_users_in_hsqldb() throws Exception {

        // GIVEN
        Model model = new ModelBuilder().withSeed("My company's users").build();
        Entity users = model.newEntity("users", 10);
        users.addProperty("id", sequence(), number().range().withMin(BigDecimal.ONE));
        users.addProperty("name", random(), string().withPattern("[A-Z]{1}[a-z]{5,29}"));
        users.addProperty("email", random(), string().withPattern("[a-z]{5,10}\\@(gmail\\.com|company\\.com|outlook\\.com)"));
        model.addEntity(users);

        model.addGenerationRule(newOneShootGenerationRule()
                .withEntity(users)
                .withFormatter(sql()
                        .addPropertyToIgnore("_id"))
                .addOutput(jdbc()
                        .withDriverClass("org.hsqldb.jdbc.JDBCDriver")
                        .withUrl("jdbc:hsqldb:mem:testdb"))
                .addOutput(stdout()));

        // WHEN
        model.generate();

        // THEN
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM users");
        assertThat(result).hasSize(10);
    }
}