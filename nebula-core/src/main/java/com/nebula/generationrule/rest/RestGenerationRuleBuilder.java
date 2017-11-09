package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.formatter.NebulaFormatters;
import com.nebula.generationrule.GenerationRule;
import com.nebula.generationrule.GenerationRuleBuilder;
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
