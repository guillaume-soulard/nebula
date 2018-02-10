package com.nebula.examples.graphql;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.generationrule.GenerationRules;
import org.apache.http.entity.ContentType;

import java.math.BigDecimal;

import static com.nebula.core.formatter.NebulaFormatters.json;
import static com.nebula.core.generators.NebulaGenerators.random;
import static com.nebula.core.generators.NebulaGenerators.sequence;
import static com.nebula.core.types.NebulaTypes.*;

public class GraphQl {

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

        model.addGenerationRule(GenerationRules.newGraphQlGenerationRule()
                .withHost("localhost")
                .withPort(3000));

        model.generate();


//        Run Queries on http://localhost:3000/ with POST method :
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
