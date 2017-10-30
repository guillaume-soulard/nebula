package com.nebula.output.http;

import com.nebula.output.Output;
import com.nebula.output.OutputBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpOutputBuilder implements OutputBuilder {

    private String url;

    public HttpOutputBuilder(String url) {
        this.url = url;
    }

    @Override
    public Output build() {
        return new HttpOutput(url, HttpClients.createDefault());
    }
}
