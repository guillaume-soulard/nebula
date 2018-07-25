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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            switch (httpVerb) {
                case HttpOutputBuilder.POST_VERB:
                    method = new HttpPost();
                    break;
                case HttpOutputBuilder.PUT_VERB:
                    method = new HttpPut();
                    break;
                case HttpOutputBuilder.PATCH_VERB:
                    method = new HttpPatch();
                    break;
                default:
                    throw new NebulaException("Http verb '" + httpVerb + "' is not supported");
            }

            staticHeaders.forEach(method::addHeader);
            if (formattedObject.getOriginalObject() != null) {
                dynamicHeaders.forEach((key, value) -> method.addHeader(key, String.valueOf(formattedObject.getOriginalObject().getValueByPath(value))));
            }

            Pattern pattern = Pattern.compile("/\\{(?<name>[a-zA-Z0-9 \\.]*)}/?");

            String finalUrl = url;
            Matcher matcher = pattern.matcher(finalUrl);
            while (matcher.find()) {

                String group = matcher.group("name");
                finalUrl = finalUrl.replaceAll("\\{" + group + "}", (String) formattedObject.getOriginalObject().getValueByPath(group));
            }

            method.setURI(URI.create(finalUrl));
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
