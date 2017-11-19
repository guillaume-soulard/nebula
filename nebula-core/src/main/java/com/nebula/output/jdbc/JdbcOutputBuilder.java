package com.nebula.output.jdbc;

import com.nebula.output.Output;
import com.nebula.output.OutputBuilder;

public class JdbcOutputBuilder implements OutputBuilder {
    private String url;
    private String driverClassName;

    @Override
    public Output build() {
        return new JdbcOutput(url, driverClassName);
    }

    public JdbcOutputBuilder withDriverClass(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public JdbcOutputBuilder withUrl(String url) {
        this.url = url;
        return this;
    }
}
