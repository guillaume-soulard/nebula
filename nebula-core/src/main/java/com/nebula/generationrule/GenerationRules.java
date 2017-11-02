package com.nebula.generationrule;

import com.nebula.generationrule.oneshoot.OneShootGenerationRuleBuilder;
import com.nebula.generationrule.rest.RestGenerationRuleBuilder;

public class GenerationRules {

    public static OneShootGenerationRuleBuilder newOneShootGenerationRule() {
        return new OneShootGenerationRuleBuilder();
    }

    public static RestGenerationRuleBuilder newRestGenerationRule() {
        return new RestGenerationRuleBuilder();
    }
}
