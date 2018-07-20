package com.nebula.core.output.http;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

class HttpOutput implements Output {

    private final String url;
    private final Map<String, String> staticHeaders;
    private final Map<String, String> dynamicHeaders;
    private final Map<String, String> dynamicPathVariables;
    private final String httpVerb;
    private final CloseableHttpClient httpClient;

    HttpOutput(String url,
               Map<String, String> staticHeaders,
               Map<String, String> dynamicHeaders,
               Map<String, String> dynamicPathVariables,
               String httpVerb,
               CloseableHttpClient httpClient) {
        this.staticHeaders = staticHeaders;
        this.dynamicHeaders = dynamicHeaders;
        this.dynamicPathVariables = dynamicPathVariables;
        this.httpVerb = httpVerb;
        this.httpClient = httpClient;
        this.url = url;
    }

    @Override
    public void open() {

    }

    @Override
    public void write(OutputParameter formattedObject) {
        try {
            HttpEntityEnclosingRequestBase method;

            if (HttpOutputBuilder.POST_VERB.equals(httpVerb)) {
                method = new HttpPost();
            } else if (HttpOutputBuilder.PUT_VERB.equals(httpVerb)) {
                method = new HttpPut();
            } else if (HttpOutputBuilder.PATCH_VERB.equals(httpVerb)) {
                method = new HttpPatch();
            } else {
                throw new NebulaException("Http verb '" + httpVerb + "' is not supported");
            }

            staticHeaders.forEach(method::addHeader);
            if (formattedObject.getOriginalObject() != null) {
                dynamicHeaders.forEach((key, value) -> method.addHeader(key, String.valueOf(formattedObject.getOriginalObject().getValueByPath(value))));
            }

            method.setURI(URI.create(url));
            method.setEntity(new StringEntity(formattedObject.getFormattedObject()));
            httpClient.execute(method);
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }
}
