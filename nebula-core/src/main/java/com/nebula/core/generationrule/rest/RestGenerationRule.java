package com.nebula.core.generationrule.rest;

import com.nebula.core.Model;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.formatter.FormatterBuilder;
import com.nebula.core.generationrule.GenerationRule;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class RestGenerationRule implements GenerationRule {

    private final Model model;
    private final String host;
    private final int port;
    private final Map<String, Formatter> contentTypeFormatterMap = new HashMap<>();
    private HttpServer server;
    private final String defaultContentType;

    RestGenerationRule(Model model, Map<String, FormatterBuilder> contentTypeFormatterMap, String defaultContentType, String host, int port) {
        this.model = model;
        this.host = host;
        this.port = port;
        this.defaultContentType = defaultContentType;

        for (Map.Entry<String, FormatterBuilder> contentTypeFormatter : contentTypeFormatterMap.entrySet()) {
            this.contentTypeFormatterMap.put(contentTypeFormatter.getKey(), contentTypeFormatter.getValue().build(model));
        }
    }

    @Override
    public void generate() {
        try {
            HttpRequestHandler requestHandler = new RequestHandler(model, contentTypeFormatterMap, defaultContentType);
            HttpProcessor httpProcessor = new HttpProcessor() {
                @Override
                public void process(HttpRequest httpRequest, HttpContext httpContext) {

                }

                @Override
                public void process(HttpResponse httpResponse, HttpContext httpContext) {

                }
            };
            SocketConfig socketConfig = SocketConfig.custom()
                    .setSoTimeout(1000)
                    .setTcpNoDelay(false)
                    .setSoReuseAddress(true)
                    .build();
            server = ServerBootstrap.bootstrap()
                    .setListenerPort(port)
                    .setLocalAddress(InetAddress.getByName(host))
                    .setHttpProcessor(httpProcessor)
                    .setSocketConfig(socketConfig)
                    .registerHandler("*", requestHandler)
                    .create();

            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        server.shutdown(5, TimeUnit.SECONDS);
    }
}
