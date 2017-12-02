package com.nebula.core.generationrule.custom;

import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;

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
