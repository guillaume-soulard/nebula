package com.nebula.output.http;

import com.nebula.core.NebulaException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HttpOutputTest {

    @Test
    public void open_should_do_nothing() throws Exception {

        // GIVEN
        String url = "localhost";
        CloseableHttpClient client = mock(CloseableHttpClient.class);
        HttpOutput output = new HttpOutput(url, client);

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
        HttpOutput output = new HttpOutput(url, client);

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
        HttpOutput output = new HttpOutput(url, client);
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
        HttpOutput output = new HttpOutput(url, client);
        String objectToWrite = "test";

        // WHEN
        output.write(objectToWrite);

        // THEN
        ArgumentCaptor<HttpUriRequest> argumentCaptor = ArgumentCaptor.forClass(HttpUriRequest.class);
        verify(client, times(1)).execute(argumentCaptor.capture());

        HttpUriRequest value = argumentCaptor.getValue();
        assertThat(value.getMethod()).isEqualTo(HttpPost.METHOD_NAME);
        assertThat(value.getURI().toURL().toString()).isEqualTo(url);
    }
}