package com.nebula.core.generationrule.oneshoot;

import com.nebula.core.Entity;
import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.output.NebulaOutputs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

class GenerationRuleBuilderTest {

    @Test
    @DisplayName("build should throw exception when entity is not specified")
    void build_should_throw_exception_when_entity_is_not_specified() {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = ModelBuilder.newEmptyModel().build();

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("entity is not specified");
    }

    @Test
    @DisplayName("build should throw exception when formatter is not specified")
    void build_should_throw_exception_when_formatter_is_not_specified() {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = ModelBuilder.newEmptyModel().build();
        Entity entity = model.newEntity("test");
        generationRuleBuilder.withEntity(entity);

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("formatter is not specified");
    }

    @Test
    @DisplayName("build should throw exception when any outputs are specified")
    void build_should_throw_exception_when_any_outputs_are_specified() {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = ModelBuilder.newEmptyModel().build();
        Entity entity = model.newEntity("test");
        generationRuleBuilder.withEntity(entity);
        generationRuleBuilder.withFormatter(NebulaFormatters.csv());

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("any outputs specified");
    }

    @Test
    @DisplayName("build should throw exception when entity non exists in model")
    void build_should_throw_exception_when_entity_non_exists_in_model() {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = ModelBuilder.newEmptyModel().build();
        generationRuleBuilder.withEntity("test");
        generationRuleBuilder.withFormatter(NebulaFormatters.csv());
        generationRuleBuilder.addOutput(NebulaOutputs.stdout());

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("entity 'test' not exists in model");
    }

    @Test
    @DisplayName("build should not throw exception when any generation constraints are specified")
    void build_should_not_throw_exception_when_any_generation_constraints_are_specified() {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = ModelBuilder.newEmptyModel().build();
        Entity entity = model.newEntity("test");
        generationRuleBuilder.withEntity(entity);
        generationRuleBuilder.withFormatter(NebulaFormatters.csv());
        generationRuleBuilder.addOutput(NebulaOutputs.stdout());

        // WHEN
        GenerationRule result = generationRuleBuilder.build(model);

        // THEN
        assertThat(result).isNotNull();
    }
}