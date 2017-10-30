package com.nebula.output.http;

import com.nebula.core.NebulaException;
import com.nebula.output.Output;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class HttpOutput implements Output {

    private final String url;
    private CloseableHttpClient httpClient;

    public HttpOutput(String url, CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
        this.url = url;
    }

    @Override
    public void open() {

    }

    @Override
    public void write(String formattedObject) {
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(formattedObject));
            httpClient.execute(post);
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
