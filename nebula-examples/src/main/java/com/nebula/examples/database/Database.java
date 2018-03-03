package com.nebula.examples.database;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.nebula.core.formatter.NebulaFormatters.sql;
import static com.nebula.core.generationconstraint.NebulaConstraints.amount;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.output.NebulaOutputs.jdbc;
import static com.nebula.core.output.NebulaOutputs.stdout;
import static com.nebula.core.types.NebulaTypes.*;

public class Database {

    public static void main(String[] args) {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/create-market-db.sql")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db);

        Model model = new ModelBuilder()
                .withSeed("Market database")
                .withoutNumberThousandSeparator()
                .build();

        Entity products = model.newEntity("Products", 1000);
        products.addProperty("id", sequence(), number().range().withMin(BigDecimal.ONE));
        products.addProperty("name", random(), string().withPattern("[A-Z]{1}[a-z]{10,24}"));
        products.addProperty("description", random(), string().withPattern("([a-z ]{10,25}){10,50}"));
        products.addProperty("price", random(), number().range().withMin("0.10").withMax("9999.99").withPrecision(2));
        products.addProperty("category", random(), number().range().withMin(BigDecimal.ONE).withMax("100"));
        model.addEntity(products);

        Entity category = model.newEntity("Category", 10);
        category.addProperty("id", sequence(), number().range().withMin(BigDecimal.ONE));
        category.addProperty("name", random(), string().withPattern("[A-Z]{1}[a-z]{10,24}"));
        category.addProperty("description", random(), string().withPattern("([a-z ]{10,25}){10,50}"));
        model.addEntity(category);

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(jdbc()
                        .withDriverClass("org.hsqldb.jdbc.JDBCDriver")
                        .withUrl("jdbc:hsqldb:mem:testdb"))
                .withFormatter(sql().addPropertyToIgnore("_id"))
                .withEntity(category));

        model.addGenerationRule(GenerationRules.newOneShootGenerationRule()
                .addOutput(jdbc()
                        .withDriverClass("org.hsqldb.jdbc.JDBCDriver")
                        .withUrl("jdbc:hsqldb:mem:testdb"))
                .withFormatter(sql().addPropertyToIgnore("_id"))
                .withEntity(products));

        model.generate();

        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM Products WHERE price < 100");

        for (Map<String, Object> stringObjectMap : result) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
                stringBuilder.append(stringObjectEntry.getKey()).append("=").append(stringObjectEntry.getValue()).append("   ");
            }
            System.out.println(stringBuilder.toString());
        }

    }
}
