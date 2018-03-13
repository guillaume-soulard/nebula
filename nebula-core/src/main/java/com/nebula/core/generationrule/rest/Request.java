package com.nebula.core.generationrule.rest;

import com.nebula.core.NebulaException;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Request {

    private final String resource;
    private Long pathIndex;
    private final Map<String, List<String>> parameters = new HashMap<>();
    private boolean hasParameters = false;

    Request(URI uri) throws UnsupportedEncodingException {

        String requestPath = uri.getPath();

        Pattern pattern = Pattern.compile("^\\/(?!\\/)(?<RESOURCE>[A-Za-z0-9]*)(\\/)?(?<INDEX>-?[0-9]*)?$");

        Matcher matcher = pattern.matcher(requestPath);

        if (!matcher.matches()) {
            throw new NebulaException("Bad request");
        }

        resource = matcher.group("RESOURCE");
        String index = matcher.group("INDEX");
        if (index != null && !index.isEmpty()) {
            try {
                pathIndex = Long.parseLong(index);
            } catch (NumberFormatException e) {
                throw new NebulaException("Bad request");
            }
        }
        parameters.put("index", Arrays.asList("0"));
        parameters.put("offset", Arrays.asList("10"));
        if (uri.getQuery() != null && !uri.getQuery().isEmpty()) {
            Map<String, List<String>> queryParameters = splitQuery(uri.getQuery());
            hasParameters = !queryParameters.isEmpty();
            parameters.putAll(queryParameters);
        }
    }

    public String getResource() {
        return resource;
    }

    public Long getLongParameter(String parameterName) {
        return Long.valueOf(parameters.get(parameterName).get(0));
    }

    public List<Long> getLongsParameter(String parameterName) {

        return parameters.get(parameterName).stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public boolean doesNotHaveParameters() {
        return !hasParameters();
    }

    private Map<String, List<String>> splitQuery(String uri) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new HashMap<>();
        final String[] pairs = uri.split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).addAll(Arrays.asList(value.split(",")));
        }
        return query_pairs;
    }

    public boolean hasParameters() {
        return hasParameters;
    }

    public long getPathIndex() {
        return pathIndex;
    }

    public boolean hasParameter(String parameterName) {
        return parameters.containsKey(parameterName);
    }

    public boolean hasPathIndex() {
        return pathIndex != null;
    }
}
