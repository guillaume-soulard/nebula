package com.nebula.core.output.socket;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class SocketOutput implements Output {

    private final String host;
    private final int port;
    private Socket socket;

    SocketOutput(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void open() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void write(String formattedObject) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            if (formattedObject != null) {
                outputStream.write(formattedObject.getBytes());
            } else {
                outputStream.write("null".getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            if (socket != null) {
                socket.close();
            } else {
                throw new NebulaException("socket is closed");
            }
        } catch (IOException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
