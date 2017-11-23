package com.nebula.examples.rest;

import com.nebula.Model;
import com.nebula.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.generationrule.GenerationRules;
import org.apache.http.entity.ContentType;

import java.math.BigDecimal;

import static com.nebula.core.NebulaGenerationTypes.*;
import static com.nebula.core.NebulaGenerators.random;
import static com.nebula.core.NebulaGenerators.sequence;
import static com.nebula.formatter.NebulaFormatters.json;

public class Rest {

    public static void main(String[] args) {
        Model model = new ModelBuilder().withSeed("users").build();

        Entity users = model.newEntity("users");
        users.addProperty("id", sequence(), number().range().withMin(BigDecimal.ZERO));
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("departments", random(), list().of(random(), entity("departments")).withMinSize(1).withMaxSize(3));

        Entity departments = model.newEntity("departments", 10);
        departments.addProperty("name", random(), picker("Dept 1")
                .addItem("Dept 2")
                .addItem("Dept 3")
                .addItem("Dept 4")
                .addItem("Dept 5")
                .addItem("Dept 6")
                .addItem("Dept 7")
                .addItem("Dept 8")
                .addItem("Dept 9")
                .addItem("Dept 10"));

        model.addEntity(users);
        model.addEntity(departments);

        model.addGenerationRule(GenerationRules.newRestGenerationRule()
                .addContentTypeFormatter(ContentType.APPLICATION_JSON, json().pretty())
                .withHost("localhost")
                .withPort(3000));

        model.generate();
    }
}
