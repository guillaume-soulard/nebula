package com.nebula.core.generationrule.graphql;

import com.nebula.core.Model;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;

public class GraphQlGenerationRuleBuilder implements GenerationRuleBuilder {

    private String host = "localhost";
    private int port = 80;
    private String handleRequestsPattern = "*";

    @Override
    public GenerationRule build(Model model) {
        return new GraphQlGenerationRule(model, host, port, handleRequestsPattern);
    }

    public GraphQlGenerationRuleBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public GraphQlGenerationRuleBuilder withPort(int port) {
        this.port = port;
        return this;
    }

    public GraphQlGenerationRuleBuilder handleRequestsOn(String handleRequestsPattern) {
        this.handleRequestsPattern = handleRequestsPattern;
        return this;
    }
}
