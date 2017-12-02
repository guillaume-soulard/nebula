package com.nebula.core.output.socket;


import com.nebula.core.output.Output;
import com.nebula.core.output.OutputBuilder;

public class SocketOutputBuilder implements OutputBuilder {


    private String host;
    private int port;

    @Override
    public Output build() {
        return new SocketOutput(host, port);
    }

    public SocketOutputBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public SocketOutputBuilder withPort(int port) {
        this.port = port;
        return this;
    }
}
