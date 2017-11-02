package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;

public class RestGenerationRuleBuilder implements GenerationRuleBuilder {

    private FormatterBuilder formatter;

    @Override
    public GenerationRule build(Model model) {
        return new RestGenerationRule(model, formatter);
    }

    public RestGenerationRuleBuilder withFormatter(FormatterBuilder formatter) {
        this.formatter = formatter;
        return this;
    }
}
