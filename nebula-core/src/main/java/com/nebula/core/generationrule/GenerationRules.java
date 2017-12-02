package com.nebula.core.generationrule;

import com.nebula.core.generationrule.custom.CustomGenerationRuleBuilder;
import com.nebula.core.generationrule.oneshoot.OneShootGenerationRuleBuilder;
import com.nebula.core.generationrule.rest.RestGenerationRuleBuilder;

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
