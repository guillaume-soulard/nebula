package com.nebula.core.generationrule.rest;

import com.nebula.core.Model;
import com.nebula.core.formatter.FormatterBuilder;
import com.nebula.core.formatter.NebulaFormatters;
import com.nebula.core.generationrule.GenerationRule;
import com.nebula.core.generationrule.GenerationRuleBuilder;
import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;

public class RestGenerationRuleBuilder implements GenerationRuleBuilder {

    private Map<String, FormatterBuilder> contentTypeFormatter = new HashMap<>();
    private String host = "localhost";
    private int port = 80;
    private String defaultContentType = ContentType.APPLICATION_JSON.getMimeType();

    @Override
    public GenerationRule build(Model model) {
        if (contentTypeFormatter.isEmpty()) {
            contentTypeFormatter.put(ContentType.APPLICATION_JSON.getMimeType(), NebulaFormatters.json().pretty().quotedFields());
        }
        return new RestGenerationRule(model, contentTypeFormatter, defaultContentType, host, port);
    }

    public RestGenerationRuleBuilder addContentTypeFormatter(ContentType contentType, FormatterBuilder formatter) {
        this.contentTypeFormatter.put(contentType.getMimeType(), formatter);
        return this;
    }

    public RestGenerationRuleBuilder setDefaultContentType(ContentType defaultContentType) {
        this.defaultContentType = defaultContentType.getMimeType();
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
