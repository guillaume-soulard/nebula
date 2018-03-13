package com.nebula.core.generationrule.graphql;

import com.nebula.core.Model;
import com.nebula.core.generationrule.GenerationRule;
import graphql.GraphQL;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class GraphQlGenerationRule implements GenerationRule {

    private final Model model;
    private final String host;
    private final int port;
    private final String handleRequestsPattern;
    private HttpServer server;

    GraphQlGenerationRule(Model model, String host, int port, String handleRequestsPattern) {
        this.model = model;
        this.host = host;
        this.port = port;
        this.handleRequestsPattern = handleRequestsPattern;
    }

    @Override
    public void generate() {
        try {
            HttpRequestHandler requestHandler = new GraphQlRequestHandler(model, buildGraphQl(model));
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
                    .registerHandler(handleRequestsPattern, requestHandler)
                    .create();

            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GraphQL buildGraphQl(Model model) {
        return new GraphQlSchemaBuilder().buildSchemaFrom(model);
    }

    public void stop() {
        server.shutdown(5, TimeUnit.SECONDS);
    }
}
