package com.nebula.generationrule;

import com.nebula.Model;
import com.nebula.core.Entity;
import com.nebula.core.NebulaException;
import com.nebula.formatter.Formatter;
import com.nebula.generationconstraint.GenerationConstraint;
import com.nebula.output.Output;

import java.util.ArrayList;
import java.util.List;

public class GenerationRuleBuilder {

    private Formatter formatter;
    private Entity entity;
    private List<Output> outputs = new ArrayList<>();
    private List<GenerationConstraint> generationConstraints = new ArrayList<>();
    private String entityName;

    public GenerationRuleBuilder withFormatter(Formatter formatter) {
        this.formatter = formatter;
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

    public GenerationRuleBuilder addOutput(Output output) {
        outputs.add(output);
        return this;
    }

    public GenerationRuleBuilder addGenerationConstraint(GenerationConstraint generationConstraint) {
        generationConstraints.add(generationConstraint);
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

        return new GenerationRule(outputs, formatter, model.iterator(entityNameToUse, model.getSeed()), generationConstraints);
    }
}
