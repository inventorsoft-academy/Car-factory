package com.academy.oop.basic.util.impl;

import com.academy.oop.basic.util.ConnectionManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

public class PostgresSqlConnectionManager implements ConnectionManager {

    private Connection connection;

    private static final String URL_PATH = "jdbc:postgresql://localhost:5432/car_factory";

    private static final String USER_NAME = "postgres";

    private static final String DB_PASSWORD = "";

    private static final String JDBC_DRIVER_NAME = "org.postgresql.Driver";


    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    @PostConstruct
    @Override
    public void getConnection() throws ClassNotFoundException, SQLException {
        connection = DriverManager
                .getConnection(URL_PATH, USER_NAME, DB_PASSWORD);
        Class.forName(JDBC_DRIVER_NAME);
    }

    @PreDestroy
    @Override
    public void closeConnection() throws SQLException {
        connection.close();
        getStatement().close();
    }

}
