package com.nebula.core.output;

import com.nebula.core.output.custom.CustomOutputBuilder;
import com.nebula.core.output.file.FileOutputBuilder;
import com.nebula.core.output.http.HttpOutputBuilder;
import com.nebula.core.output.jdbc.JdbcOutputBuilder;
import com.nebula.core.output.socket.SocketOutputBuilder;
import com.nebula.core.output.stdout.StandardOutputOutputBuilder;

public final class NebulaOutputs {

    public static StandardOutputOutputBuilder stdout() {
        return new StandardOutputOutputBuilder();
    }

    public static FileOutputBuilder file(String path) {
        return new FileOutputBuilder(path);
    }

    public static HttpOutputBuilder http(String url) { return new HttpOutputBuilder(url); }

    public static JdbcOutputBuilder jdbc() { return new JdbcOutputBuilder(); }

    public static SocketOutputBuilder socket() { return new SocketOutputBuilder(); }

    public static CustomOutputBuilder custom(Output output) { return new CustomOutputBuilder(output); }
}
