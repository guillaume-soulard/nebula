package com.nebula.generationrule;

import com.nebula.generationrule.oneshoot.OneShootGenerationRuleBuilder;

public class GenerationRules {

    public static OneShootGenerationRuleBuilder newOneShootGenerationRule() {
        return new OneShootGenerationRuleBuilder();
    }
}
