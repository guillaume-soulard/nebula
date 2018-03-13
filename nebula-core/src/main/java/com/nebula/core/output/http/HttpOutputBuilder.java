package com.nebula.core.output.http;

import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpOutputBuilder implements OutputBuilder {

    private final String url;

    public HttpOutputBuilder(String url) {
        this.url = url;
    }

    @Override
    public Output build() {
        return new HttpOutput(url, HttpClients.createDefault());
    }
}
