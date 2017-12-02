package com.nebula.core.output.jdbc;

import com.nebula.core.NebulaException;
import com.nebula.core.output.Output;

import java.sql.*;

class JdbcOutput implements Output {

    private Connection connection;
    private String url;
    private String driverClassName;

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
    public void write(String formattedObject) {
        try {
            if (!formattedObject.isEmpty() && !formattedObject.equals("\n")) {
                connection.prepareStatement(formattedObject).execute();
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
