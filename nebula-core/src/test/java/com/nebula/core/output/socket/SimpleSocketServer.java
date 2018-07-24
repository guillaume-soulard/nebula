package com.nebula.core.output.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class SimpleSocketServer extends Thread {

    private ServerSocket serverSocket;
    private boolean running = true;
    private final List<String> receivedData = new ArrayList<>();

    SimpleSocketServer() throws IOException {
        this.serverSocket = new ServerSocket(3000);
    }

    public void stopServer() throws IOException {
        running = false;
        serverSocket.close();
    }

    @Override
    public void run() {
        try {
            while (running) {
                    Socket acceptedSocket = serverSocket.accept();
                    InputStream inputStream = acceptedSocket.getInputStream();
                    while (running) {
                        if (inputStream.available() > 0) {
                            byte[] bytes = new byte[inputStream.available()];
                            if (inputStream.read(bytes) != -1) {
                                String data = new String(bytes);
                                receivedData.add(data);
                            }
                        }
                    }
            }
        } catch (IOException e) {
            if (!e.getMessage().equals("Socket closed")) {
                e.printStackTrace();
            }
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getReceivedData() {
        return receivedData;
    }
}
