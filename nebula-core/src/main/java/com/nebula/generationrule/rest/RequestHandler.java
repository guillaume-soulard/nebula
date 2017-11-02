package com.nebula.generationrule.rest;

import com.nebula.Model;
import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.NebulaException;
import com.nebula.formatter.Formatter;
import com.nebula.formatter.NebulaFormatters;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class RequestHandler implements HttpRequestHandler {

    private Model model;
    private Formatter formatter;

    public RequestHandler(Model model, Formatter formatter) {
        this.model = model;
        this.formatter = formatter;
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        try {
            if (HttpGet.METHOD_NAME.equals(httpRequest.getRequestLine().getMethod())) {
                sendGetQueryResult(httpRequest, httpResponse);
            } else {
                sendNotSupportedMethod(httpResponse, 405);
            }
        } catch (Exception e) {
            sendError(httpResponse, e);
        }
    }

    private void sendError(HttpResponse httpResponse, Exception e) throws UnsupportedEncodingException {
        httpResponse.setStatusCode(500);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        httpResponse.setEntity(new StringEntity("{\"error\":\"" + e.getMessage() + "\", \"trace\":\"" + sw.toString() + "\"}"));
    }

    private void sendGetQueryResult(HttpRequest httpRequest, HttpResponse httpResponse) throws UnsupportedEncodingException {
        Request request;
        try {
            request = new Request(URI.create(httpRequest.getRequestLine().getUri()));
        } catch (NebulaException e) {
            httpResponse.setStatusCode(400);
            return;
        }

        if (!request.hasPathIndex()) {
            sendResourceQuery(httpResponse, request);
        } else if (request.doesNotHaveParameters() && request.hasPathIndex()) {
            sendResourceQueryByIndex(httpResponse, request);
        } else {
            httpResponse.setStatusCode(400);
        }
    }

    private void sendResourceQueryByIndex(HttpResponse httpResponse, Request request) throws UnsupportedEncodingException {

        if (isEntityExists(request.getResource())) {
            GeneratedObject generatedObject = model.generateEntityObject(request.getResource(), request.getPathIndex());

            String formattedObject = formatter.formatGeneratedObject(generatedObject);

            httpResponse.setStatusCode(200);
            httpResponse.setHeader("Accept", "text/javascript");
            httpResponse.setEntity(new StringEntity(formattedObject));
        } else {
            httpResponse.setStatusCode(404);
            httpResponse.setEntity(new StringEntity("{\"error\": \"Resource '" + request.getResource() + "' not found\"}"));
            httpResponse.setHeader("Accept", "text/javascript");
        }
    }

    private boolean isEntityExists(String resource) {
        boolean entityExists = false;
        for (Entity entity : model.getEntities()) {
            if (entity.getName().equals(resource)) {
                entityExists = true;
            }
        }
        return entityExists;
    }

    private void sendResourceQuery(HttpResponse httpResponse, Request request) throws UnsupportedEncodingException {
        if (isEntityExists(request.getResource())) {
            List<GeneratedObject> generatedObjects = new ArrayList<>();

            if (request.hasParameter("indices")) {
                for (long index : request.getLongsParameter("indices")) {
                    generatedObjects.add(model.generateEntityObject(request.getResource(), index));
                }

            } else {
                long startIndex = request.getLongParameter("skip");
                long endIndex = startIndex + request.getLongParameter("count") - 1;
                for (long i = startIndex; i <= endIndex; i++) {
                    generatedObjects.add(model.generateEntityObject(request.getResource(), i));
                }
            }
            String formattedObject = formatter.formatGeneratedObject(new GeneratedObject(generatedObjects));
            httpResponse.setStatusCode(200);
            httpResponse.setHeader("Accept", "text/javascript");
            httpResponse.setEntity(new StringEntity(formattedObject));
        } else {
            httpResponse.setStatusCode(404);
            httpResponse.setEntity(new StringEntity("{\"error\": \"Resource '" + request.getResource() + "' not found\"}"));
            httpResponse.setHeader("Accept", "text/javascript");
        }
    }

    private void sendNotSupportedMethod(HttpResponse httpResponse, int code) {
        httpResponse.setStatusCode(code);
    }
}
