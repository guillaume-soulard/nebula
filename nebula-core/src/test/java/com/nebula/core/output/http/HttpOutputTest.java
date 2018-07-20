package com.nebula.core.output.http;

import com.nebula.core.GeneratedObject;
import com.nebula.core.GeneratedProperty;
import com.nebula.core.NebulaException;
import com.nebula.core.output.OutputParameter;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HttpOutputTest {

    @Test
    public void open_should_do_nothing() {

        // GIVEN
        String url = "localhost";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);

        // WHEN
        output.open();

        // THEN
        verifyNoMoreInteractions(client);
    }

    @Test
    public void close_should_call_close_on_client() throws Exception {

        // GIVEN
        String url = "localhost";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);

        // WHEN
        output.close();

        // THEN
        verify(client, times(1)).close();
    }

    @Test
    public void close_should_throw_NebulaException_when_close_throw_exception_and_has_same_message() throws Exception {

        // GIVEN
        String url = "localhost";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        doThrow(new IOException("test")).when(client).close();

        // WHEN
        catchException(output).close();

        // THEN
        assertThat((Throwable) caughtException()).isInstanceOf(NebulaException.class).hasMessage("test");
    }

    @Test
    public void write_should_call_execute() throws Exception {

        // GIVEN
        String url = "http://localhost:3000/resource";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        String objectToWrite = "test";
        OutputParameter formattedObject = new OutputParameter(objectToWrite, null);

        // WHEN
        output.write(formattedObject);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getMethod()).isEqualTo(HttpPost.METHOD_NAME);
        assertThat(value.getURI().toURL().toString()).isEqualTo(url);
    }

    @Test
    public void write_should_call_execute_with_put_verb() throws Exception {

        // GIVEN
        String url = "http://localhost:3000/resource";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.PUT_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        String objectToWrite = "test";
        OutputParameter formattedObject = new OutputParameter(objectToWrite, null);

        // WHEN
        output.write(formattedObject);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getMethod()).isEqualTo(HttpPut.METHOD_NAME);
    }

    @Test
    public void write_should_call_execute_with_patch_verb() throws Exception {

        // GIVEN
        String url = "http://localhost:3000/resource";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.PATCH_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        String objectToWrite = "test";
        OutputParameter formattedObject = new OutputParameter(objectToWrite, null);

        // WHEN
        output.write(formattedObject);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getMethod()).isEqualTo(HttpPatch.METHOD_NAME);
    }

    @Test
    public void write_should_call_execute_with_one_header_dynamic() throws Exception {

        // GIVEN
        String url = "http://localhost:3000/resource";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        Map<String, String> dynamicHeaders = new HashMap<>();
        dynamicHeaders.put("headerName", "dept.name");
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        List<GeneratedProperty> userGeneratedProperties = new ArrayList<>();
        userGeneratedProperties.add(new GeneratedProperty("name", new GeneratedObject("John"), null));
        List<GeneratedProperty> deptGeneratedProperties = new ArrayList<>();
        deptGeneratedProperties.add(new GeneratedProperty("name", new GeneratedObject("test"), null));
        GeneratedObject dept = new GeneratedObject(deptGeneratedProperties);
        userGeneratedProperties.add(new GeneratedProperty("dept", dept, null));
        GeneratedObject generatedObject = new GeneratedObject(userGeneratedProperties);
        String objectToWrite = "{ name }";
        OutputParameter formattedObject = new OutputParameter(objectToWrite, generatedObject);

        // WHEN
        output.write(formattedObject);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getAllHeaders()).hasSize(1);
        assertThat(value.getAllHeaders()[0].getName()).isEqualTo("headerName");
        assertThat(value.getAllHeaders()[0].getValue()).isEqualTo("test");
    }

    @Test
    public void write_should_call_execute_with_one_header_static() throws Exception {

        // GIVEN
        String url = "http://localhost:3000/resource";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        Map<String, String> staticHeaders = new HashMap<>();
        staticHeaders.put("headerName", "headerValue");
        Map<String, String> dynamicHeaders = new HashMap<>();
        Map<String, String> dynamicPathVariable = new HashMap<>();
        String httpVerb = HttpOutputBuilder.POST_VERB;
        HttpOutput output = new HttpOutput(url, staticHeaders, dynamicHeaders, dynamicPathVariable, httpVerb, client);
        String objectToWrite = "test";
        OutputParameter formattedObject = new OutputParameter(objectToWrite, null);

        // WHEN
        output.write(formattedObject);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getAllHeaders()).hasSize(1);
        assertThat(value.getAllHeaders()[0].getName()).isEqualTo("headerName");
        assertThat(value.getAllHeaders()[0].getValue()).isEqualTo("headerValue");
    }
}