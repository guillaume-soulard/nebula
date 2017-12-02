package com.nebula.core.generationrule.oneshoot;

import com.nebula.core.Model;
import com.nebula.core.ModelBuilder;
import com.nebula.core.Entity;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.output.NebulaOutputs;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class GenerationRuleBuilderTest {

    @Test
    public void build_should_throw_exception_when_entity_is_not_specified() throws Exception {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = new ModelBuilder().build();

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("entity is not specified");
    }

    @Test
    public void build_should_throw_exception_when_formatter_is_not_specified() throws Exception {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = new ModelBuilder().build();
        Entity entity = model.newEntity("test");
        generationRuleBuilder.withEntity(entity);

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("formatter is not specified");
    }

    @Test
    public void build_should_throw_exception_when_any_outputs_are_specified() throws Exception {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = new ModelBuilder().build();
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
    public void build_should_throw_exception_when_entity_non_exists_in_model() throws Exception {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = new ModelBuilder().build();
        Entity entity = model.newEntity("test");
        generationRuleBuilder.withEntity(entity);
        generationRuleBuilder.withFormatter(NebulaFormatters.csv());
        generationRuleBuilder.addOutput(NebulaOutputs.stdout());

        // WHEN
        catchException(generationRuleBuilder).build(model);

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class)
                .hasMessage("entity 'test' not exists in model");
    }

    @Test
    public void build_should_not_throw_exception_when_any_generation_constraints_are_specified() throws Exception {

        // GIVEN
        OneShootGenerationRuleBuilder generationRuleBuilder = new OneShootGenerationRuleBuilder();
        Model model = new ModelBuilder().build();
        Entity entity = model.newEntity("test");
        model.addEntity(entity);
        generationRuleBuilder.withEntity(entity);
        generationRuleBuilder.withFormatter(NebulaFormatters.csv());
        generationRuleBuilder.addOutput(NebulaOutputs.stdout());

        // WHEN
        GenerationRule result = generationRuleBuilder.build(model);

        // THEN
        assertThat(result).isNotNull();
    }
}