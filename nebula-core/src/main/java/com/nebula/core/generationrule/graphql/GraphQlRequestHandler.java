package com.nebula.core.generationrule.graphql;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.Model;
import com.nebula.core.NebulaException;
import com.nebula.core.formatter.Formatter;
import com.nebula.core.formatter.NebulaFormatters;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nebula.core.types.NebulaTypes.string;

class GraphQlRequestHandler implements HttpRequestHandler {

    private final Model model;
    private final Formatter jsonFormatter;
    private final GraphQL graphQL;

    GraphQlRequestHandler(Model model, GraphQL graphQL) {
        this.model = model;
        this.graphQL = graphQL;
        jsonFormatter = NebulaFormatters.json()
                .pretty().quotedFields().build(model);
    }

    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws IOException {
        try {
            if (HttpPost.METHOD_NAME.equals(httpRequest.getRequestLine().getMethod())) {
                executeGraphQlQueryAndSendResult(httpRequest, httpResponse);
            } else {
                sendNotSupportedMethod(httpResponse);
            }
        } catch (Exception e) {
            sendError(httpRequest, httpResponse, e);
        }
    }

    private void executeGraphQlQueryAndSendResult(HttpRequest httpRequest, HttpResponse httpResponse) {

        try {
            InputStream content = ((BasicHttpEntityEnclosingRequest) httpRequest).getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String query = bufferedReader.lines().collect(Collectors.joining());
            ExecutionResult queryResult = graphQL.execute(query);

            if (queryResult.getErrors().isEmpty()) {
                httpResponse.setStatusCode(200);
                httpResponse.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
                httpResponse.setEntity(new StringEntity(queryResult.getData().toString()));
            } else {
                httpResponse.setStatusCode(500);
                httpResponse.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
                httpResponse.setEntity(new StringEntity(queryResult.getErrors().toString()));
            }


        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
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
        generatedProperties.add(new GeneratedProperty("error", GeneratedObject.of(message), string().build(model)));
        generatedProperties.add(new GeneratedProperty("detail", GeneratedObject.of(detail), string().build(model)));
        return jsonFormatter.formatGeneratedObject(GeneratedObject.of(generatedProperties));
    }

    private void sendNotSupportedMethod(HttpResponse httpResponse) {
        httpResponse.setStatusCode(405);
    }
}
