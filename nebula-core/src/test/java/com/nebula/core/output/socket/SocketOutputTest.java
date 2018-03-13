package com.nebula.core.output.socket;

import com.nebula.core.NebulaException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

@Ignore("Sometime this test class fail on maven build")
public class SocketOutputTest {

    private SimpleSocketServer server;

    @Before
    public void setUp() throws Exception {
        server = new SimpleSocketServer();
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    public void open_should_open_socket() {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN
        socketOutput.open();

        // THEN
        assertThat(socketOutput.getSocket().isClosed()).isFalse();
    }

    @Test
    public void open_should_throw_exception_when_unknown_host_not_listen_the_given_port() {

        // GIVEN
        String host = "aqzsedrftgyhujikolpmwxcvbn";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN
        catchException(socketOutput).open();

        // THEN
        assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage(host);
    }

    @Test
    public void open_should_throw_exception_when_right_listen_the_given_another_port() {

        // GIVEN
        String host = "localhost";
        int port = 9999;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN
        catchException(socketOutput).open();

        // THEN
        assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("Connection refused (Connection refused)");
    }

    @Test
    public void close_should_close_socket() {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);
        socketOutput.open();

        // WHEN
        socketOutput.close();

        // THEN
        assertThat(socketOutput.getSocket().isClosed()).isTrue();
    }

    @Test
    public void close_should_throw_exception_when_socket_is_not_open() {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN
        catchException(socketOutput).close();

        // THEN
        assertThat((Exception) caughtException()).isInstanceOf(NebulaException.class).hasMessage("socket is closed");
    }

    @Test
    public void write_should_send_test() throws Exception {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);
        socketOutput.open();

        // WHEN
        socketOutput.write("test");

        // THEN
        Thread.sleep(100); // wait for the server to receive data
        socketOutput.close();
        assertThat(server.getReceivedData()).containsOnly("test");
    }

    @Test
    public void write_should_send_null() {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);
        socketOutput.open();

        // WHEN
        socketOutput.write(null);

        // THEN
        socketOutput.close();
        assertThat(server.getReceivedData()).containsOnly("null");
    }
}
