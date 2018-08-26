package com.nebula.core.output.socket;

import com.nebula.core.NebulaException;
import com.nebula.core.output.OutputParameter;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Sometime this test class fail on maven build")
class SocketOutputTest {

    private SimpleSocketServer server;

    @BeforeEach
    void setUp() throws Exception {
        server = new SimpleSocketServer();
        server.start();
    }

    @AfterEach
    void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    @DisplayName("open should open socket")
    void open_should_open_socket() {

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
    @DisplayName("open should throw exception when unknown host not listen the given port")
    void open_should_throw_exception_when_unknown_host_not_listen_the_given_port() {

        // GIVEN
        String host = "aqzsedrftgyhujikolpmwxcvbn";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN

        // THEN
        assertThatThrownBy(socketOutput::open)
                .isInstanceOf(NebulaException.class)
                .hasMessage(host);
    }

    @Test
    @DisplayName("open should throw exception when right listen the given another port")
    void open_should_throw_exception_when_right_listen_the_given_another_port() {

        // GIVEN
        String host = "localhost";
        int port = 9999;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN

        // THEN
        assertThatThrownBy(socketOutput::open)
                .isInstanceOf(NebulaException.class)
                .hasMessage("Connection refused (Connection refused)");
    }

    @Test
    @DisplayName("close should close socket")
    void close_should_close_socket() {

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
    @DisplayName("close should throw exception when socket is not open")
    void close_should_throw_exception_when_socket_is_not_open() {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);

        // WHEN

        // THEN
        assertThatThrownBy(socketOutput::close)
                .isInstanceOf(NebulaException.class)
                .hasMessage("socket is closed");
    }

    @Test
    @DisplayName("write should send test")
    void write_should_send_test() throws Exception {

        // GIVEN
        String host = "localhost";
        int port = 3000;
        SocketOutput socketOutput = new SocketOutput(host, port);
        socketOutput.open();

        // WHEN
        socketOutput.write(new OutputParameter("test", null));

        // THEN
        Thread.sleep(100); // wait for the server to receive data
        socketOutput.close();
        assertThat(server.getReceivedData()).containsOnly("test");
    }

    @Test
    @DisplayName("write should send null")
    void write_should_send_null() {

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
