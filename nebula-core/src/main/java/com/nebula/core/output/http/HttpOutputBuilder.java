package com.nebula.core.output.http;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;
import java.util.Map;

public class HttpOutputBuilder implements OutputBuilder {

    static final String POST_VERB = "POST";
    static final String PUT_VERB = "PUT";
    static final String PATCH_VERB = "PATCH";
    private final String url;
    private String httpVerb;
    private Map<String, String> staticHeaders = new HashMap<>();
    private Map<String, String> dynamicHeaders = new HashMap<>();
    private Map<String, String> dynamicPathVariables = new HashMap<>();

    public HttpOutputBuilder(String url) {
        this.url = url;
        this.httpVerb = POST_VERB;
    }

    public HttpOutputBuilder usingPostVerb() {
        httpVerb = POST_VERB;
        return this;
    }

    public HttpOutputBuilder usingPutVerb() {
        httpVerb = PUT_VERB;
        return this;
    }

    public HttpOutputBuilder usingPatchVerb() {
        httpVerb = PATCH_VERB;
        return this;
    }

    public HttpOutputBuilder withHeader(String headerName, String headerValue) {
        staticHeaders.put(headerName, headerValue);
        return this;
    }

    public HttpOutputBuilder withDynamicHeader(String headerName, String propertyValueToUse) {
        dynamicHeaders.put(headerName, propertyValueToUse);
        return this;
    }

    public HttpOutputBuilder withDynamicPathVariable(String pathVariableName, String propertyValueToUse) {
        dynamicPathVariables.put(pathVariableName, propertyValueToUse);
        return this;
    }

    @Override
    public Output build() {
        return new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariables, httpVerb, HttpClients.createDefault());
    }
}
