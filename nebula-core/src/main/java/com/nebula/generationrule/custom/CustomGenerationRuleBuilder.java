package com.nebula.generationrule.custom;

import com.nebula.Model;
import com.nebula.core.NebulaException;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;

public class CustomGenerationRuleBuilder implements GenerationRuleBuilder {

    private GenerationRule generationRule;

    public CustomGenerationRuleBuilder(GenerationRule generationRule) {
        if (generationRule == null) {
            throw new NebulaException("generationRule is null");
        }
        this.generationRule = generationRule;
    }

    @Override
    public GenerationRule build(Model model) {
        return generationRule;
    }
}
