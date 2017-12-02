package com.nebula.generationrule;

import com.nebula.generationrule.custom.CustomGenerationRuleBuilder;
import com.nebula.generationrule.oneshoot.OneShootGenerationRuleBuilder;
import com.nebula.generationrule.rest.RestGenerationRuleBuilder;

public class GenerationRules {

    public static OneShootGenerationRuleBuilder newOneShootGenerationRule() {
        return new OneShootGenerationRuleBuilder();
    }

    public static RestGenerationRuleBuilder newRestGenerationRule() {
        return new RestGenerationRuleBuilder();
    }

    public static CustomGenerationRuleBuilder custom(GenerationRule generationRule) {
        return new CustomGenerationRuleBuilder(generationRule);
    }
}
