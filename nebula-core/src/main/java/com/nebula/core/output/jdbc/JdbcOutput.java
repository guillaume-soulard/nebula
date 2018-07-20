package com.nebula.core.output.jdbc;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;
import com.nebula.core.output.OutputParameter;

import java.sql.*;

class JdbcOutput implements Output {

    private Connection connection;
    private final String url;
    private final String driverClassName;

    JdbcOutput(String url, String driverClassName) {
        this.url = url;
        this.driverClassName = driverClassName;
    }

    @Override
    public void open() {
        try {
            Driver driver = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void write(OutputParameter formattedObject) {
        try {
            if (!formattedObject.getFormattedObject().isEmpty()
                    && !formattedObject.getFormattedObject().equals("\n")
                    && !formattedObject.getFormattedObject().equals("\r\n")) {
                connection.prepareStatement(formattedObject.getFormattedObject()).execute();
            }
        } catch (SQLException e) {
            throw new NebulaException(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new NebulaException(e.getMessage());
        }
    }
}
