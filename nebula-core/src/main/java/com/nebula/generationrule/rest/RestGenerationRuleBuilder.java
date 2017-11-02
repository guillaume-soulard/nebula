package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;

public class RestGenerationRuleBuilder implements GenerationRuleBuilder {

    private FormatterBuilder formatter;
    private String host = "localhost";
    private int port = 80;

    @Override
    public GenerationRule build(Model model) {
        return new RestGenerationRule(model, formatter, host, port);
    }

    public RestGenerationRuleBuilder withFormatter(FormatterBuilder formatter) {
        this.formatter = formatter;
        return this;
    }

    public RestGenerationRuleBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public RestGenerationRuleBuilder withPort(int port) {
        this.port = port;
        return this;
    }
}
