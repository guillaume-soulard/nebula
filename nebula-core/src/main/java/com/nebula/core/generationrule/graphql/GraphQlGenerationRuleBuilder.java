package com.nebula.core.generationrule.graphql;

import com.nebula.core.Model;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;

public class GraphQlGenerationRuleBuilder implements GenerationRuleBuilder {

    private String host = "localhost";
    private int port = 80;

    @Override
    public GenerationRule build(Model model) {
        return new GraphQlGenerationRule(model, host, port);
    }

    public GraphQlGenerationRuleBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public GraphQlGenerationRuleBuilder withPort(int port) {
        this.port = port;
        return this;
    }
}
