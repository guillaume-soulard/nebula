package com.nebula.core.generationrule.rest;

import com.nebula.core.Model;
import com.nebula.core.Entity;
import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.Formatter;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nebula.core.types.NebulaTypes.string;

class RequestHandler implements HttpRequestHandler {

    private Model model;
    private Map<String, Formatter> contentTypeFormatterMap;
    private String defaultContentType;

    public RequestHandler(Model model, Map<String, Formatter> contentTypeFormatterMap, String defaultContentType) {
        this.model = model;
        this.contentTypeFormatterMap = contentTypeFormatterMap;
        this.defaultContentType = defaultContentType;
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
            sendError(httpRequest, httpResponse, e);
        }
    }

    private void sendError(HttpRequest httpRequest, HttpResponse httpResponse, Exception e) throws UnsupportedEncodingException {
        httpResponse.setStatusCode(500);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String error = getFormattedError(httpRequest, e.getMessage(), sw.toString());
        httpResponse.setEntity(new StringEntity(error));
    }

    private String getFormattedError(HttpRequest httpRequest, String message, String detail) {
        List<GeneratedProperty> generatedProperties = new ArrayList<>();
        generatedProperties.add(new GeneratedProperty("error", new GeneratedObject(message), string().build(model)));
        generatedProperties.add(new GeneratedProperty("detail", new GeneratedObject(detail), string().build(model)));
        return getFormatter(httpRequest).formatGeneratedObject(new GeneratedObject(generatedProperties));
    }

    private Formatter getFormatter(HttpRequest httpRequest) {
        Header acceptHeader = httpRequest.getFirstHeader(HttpHeaders.ACCEPT);

        if (acceptHeader == null) {
            acceptHeader = new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        }

        String mimeType = acceptHeader.getValue().toLowerCase();

        if (contentTypeFormatterMap.containsKey(mimeType)) {
            return contentTypeFormatterMap.get(mimeType);
        } else {
            return contentTypeFormatterMap.get(defaultContentType);
        }
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
            sendResourceQuery(httpResponse, request, httpRequest);
        } else if (request.doesNotHaveParameters() && request.hasPathIndex()) {
            sendResourceQueryByIndex(httpRequest, httpResponse, request);
        } else {
            httpResponse.setStatusCode(400);
        }
    }

    private void sendResourceQueryByIndex(HttpRequest httpRequest, HttpResponse httpResponse, Request request) throws UnsupportedEncodingException {

        if (isEntityExists(request.getResource())) {
            GeneratedObject generatedObject = model.generateEntityObject(request.getResource(), request.getPathIndex());

            String formattedObject = getFormatter(httpRequest).formatGeneratedObject(generatedObject);

            httpResponse.setStatusCode(200);
            httpResponse.setHeader("Accept", "text/javascript");
            httpResponse.setEntity(new StringEntity(formattedObject));
        } else {
            httpResponse.setStatusCode(404);
            httpResponse.setEntity(new StringEntity(getFormattedError(httpRequest, "Not found", "The resource '" + request.getResource() + "' is not found in current model")));
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

    private void sendResourceQuery(HttpResponse httpResponse, Request request, HttpRequest httpRequest) throws UnsupportedEncodingException {
        if (isEntityExists(request.getResource())) {
            List<GeneratedObject> generatedObjects = new ArrayList<>();

            if (request.hasParameter("indices")) {
                generatedObjects.addAll(request.getLongsParameter("indices").stream().map(index -> model.generateEntityObject(request.getResource(), index)).collect(Collectors.toList()));

            } else {
                long startIndex = request.getLongParameter("skip");
                long endIndex = startIndex + request.getLongParameter("count") - 1;
                for (long i = startIndex; i <= endIndex; i++) {
                    generatedObjects.add(model.generateEntityObject(request.getResource(), i));
                }
            }
            String formattedObject = getFormatter(httpRequest).formatGeneratedObject(new GeneratedObject(generatedObjects));
            httpResponse.setStatusCode(200);
            httpResponse.setHeader("Accept", "text/javascript");
            httpResponse.setEntity(new StringEntity(formattedObject));
        } else {
            httpResponse.setStatusCode(404);
            httpResponse.setEntity(new StringEntity(getFormattedError(httpRequest, "Not found", "The resource '" + request.getResource() + "' is not found in current model")));
            httpResponse.setHeader("Accept", "text/javascript");
        }
    }

    private void sendNotSupportedMethod(HttpResponse httpResponse, int code) {
        httpResponse.setStatusCode(code);
    }
}
