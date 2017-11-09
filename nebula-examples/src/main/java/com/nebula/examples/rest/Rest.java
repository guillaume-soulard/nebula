package com.nebula.examples.rest;

import com.nebula.Model;
import com.nebula.core.Entity;
import com.nebula.generationrule.GenerationRules;

import static com.nebula.Nebula.*;
import static com.nebula.core.NebulaGenerationTypes.*;
import static com.nebula.core.NebulaGenerators.random;
import static com.nebula.formatter.NebulaFormatters.json;

public class Rest {

    public static void main(String[] args) {
        Model model = newModel();
        model.setSeed("users");

        Entity users = model.newEntity("users");
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("departments", random(), list().of(random(), entity("departments")).withMinSize(1).withMaxSize(3));

        Entity departments = model.newEntity("departments", 10);
        departments.addProperty("name", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));

        model.addEntity(users);
        model.addEntity(departments);

        model.addGenerationRule(GenerationRules.newRestGenerationRule()
                .addContentTypeFormatter(json().pretty())
                .withHost("localhost")
                .withPort(3000));

        model.generate();
    }
}
