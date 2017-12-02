package com.nebula.core.generationrule.oneshoot;

import com.nebula.core.Model;
import com.nebula.core.Entity;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.FormatterBuilder;
import com.nebula.core.generationconstraint.GenerationConstraint;
import com.nebula.core.generationconstraint.amount.GenerationConstraintBuilder;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

import java.util.ArrayList;
import java.util.List;

public class OneShootGenerationRuleBuilder implements GenerationRuleBuilder {

    private FormatterBuilder formatterBuilder;
    private Entity entity;
    private List<Output> outputs = new ArrayList<>();
    private List<GenerationConstraint> generationConstraints = new ArrayList<>();
    private String entityName;

    public OneShootGenerationRuleBuilder withFormatter(FormatterBuilder formatterBuilder) {
        this.formatterBuilder = formatterBuilder;
        return this;
    }

    public OneShootGenerationRuleBuilder withEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public OneShootGenerationRuleBuilder withEntity(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public OneShootGenerationRuleBuilder addOutput(OutputBuilder outputBuilder) {
        outputs.add(outputBuilder.build());
        return this;
    }

    public OneShootGenerationRuleBuilder addGenerationConstraint(GenerationConstraintBuilder generationConstraintBuilder) {
        generationConstraints.add(generationConstraintBuilder.build());
        return this;
    }

    public GenerationRule build(Model model) {

        String entityNameToUse = null;

        if (entity != null) {
            entityNameToUse = entity.getName();
        } else if (entityName != null && !entityName.isEmpty()) {
            entityNameToUse = entityName;
        } else {
            throw new NebulaException("entity is not specified");
        }

        if (formatterBuilder == null) {
            throw new NebulaException("formatter is not specified");
        }

        if (outputs.isEmpty()) {
            throw new NebulaException("any outputs specified");
        }

        return new OneShootGenerationRule(outputs, formatterBuilder.build(model), model.iterator(entityNameToUse, model.getSeed()), generationConstraints);
    }
}
