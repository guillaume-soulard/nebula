package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.FormatterBuilder;
import com.nebula.generationrule.GenerationRule;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class RestGenerationRule implements GenerationRule {

    private Model model;
    private String host;
    private int port;
    private Formatter formatter;
    private HttpServer server;

    public RestGenerationRule(Model model, FormatterBuilder formatter, String host, int port) {
        this.model = model;
        this.host = host;
        this.port = port;
        this.formatter = formatter.build(model);
    }

    @Override
    public void generate() {
        try {
            HttpRequestHandler requestHandler = new RequestHandler(model, formatter);
            HttpProcessor httpProcessor = new HttpProcessor() {
                @Override
                public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

                }

                @Override
                public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {

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
