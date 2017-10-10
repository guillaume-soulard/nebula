package com.nebula.generationrule;

import com.nebula.Model;
import com.nebula.core.Entity;
import com.nebula.core.NebulaException;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.generationconstraint.GenerationConstraint;
import com.nebula.generationconstraint.amount.GenerationConstraintBuilder;
import com.nebula.output.Output;
import com.nebula.output.OutputBuilder;

import java.util.ArrayList;
import java.util.List;

public class GenerationRuleBuilder {

    private FormatterBuilder formatterBuilder;
    private Entity entity;
    private List<Output> outputs = new ArrayList<>();
    private List<GenerationConstraint> generationConstraints = new ArrayList<>();
    private String entityName;

    public GenerationRuleBuilder withFormatter(FormatterBuilder formatterBuilder) {
        this.formatterBuilder = formatterBuilder;
        return this;
    }

    public GenerationRuleBuilder withEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public GenerationRuleBuilder withEntity(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public GenerationRuleBuilder addOutput(OutputBuilder outputBuilder) {
        outputs.add(outputBuilder.build());
        return this;
    }

    public GenerationRuleBuilder addGenerationConstraint(GenerationConstraintBuilder generationConstraintBuilder) {
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

        return new GenerationRule(outputs, formatterBuilder.build(model), model.iterator(entityNameToUse, model.getSeed()), generationConstraints);
    }
}
