package com.nebula.examples.graphql;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;

import java.math.BigDecimal;

import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.types.NebulaTypes.*;

class GraphQl {

    public static void main(String[] args) {
        Model model = ModelBuilder.newModel().withSeed("users").build();

        Entity users = model.newEntity("users");
        users.addProperty("id", sequence(), number().range().withMin(BigDecimal.ZERO));
        users.addProperty("firstName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("lastName", random(), string().withPattern("[A-Z]{1}[a-z]{10,25}"));
        users.addProperty("departments", random(), list().of(random(), entity("departments")).withMinSize(1).withMaxSize(3));

        Entity departments = model.newEntity("departments", 10);
        departments.addProperty("name", random(), amongItems("Dept 1",
                "Dept 2",
                "Dept 3",
                "Dept 4",
                "Dept 5",
                "Dept 6",
                "Dept 7",
                "Dept 8",
                "Dept 9",
                "Dept 10"));

        model.addGenerationRule(GenerationRules.newGraphQlGenerationRule()
                .withHost("localhost")
                .handleRequestsOn("/graphQl")
                .withPort(3000));

        model.generate();


//        Run Queries on http://localhost:3000/graphQl with POST method :
//
//        To get only one user :
//
//        query {
//            one_users(_id: 2588755) {
//                _id
//                        firstName
//                departments {
//                    name
//                }
//            }
//        }
//
//        Or to get a range of user :
//
//        query {
//            many_users(_id: 2588755, offset: 10) {
//                _id
//                        firstName
//                departments {
//                    name
//                }
//            }
//        }
    }
}
