package com.academy.oop.basic.util.impl;

import com.academy.oop.basic.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

@Component
@PropertySource("classpath:application.properties")
public class PostgresSqlConnectionManager implements ConnectionManager {

    private Connection connection;

    @Value("${app.datasource.url}")
    private String URL_PATH;

    @Value("${app.datasource.username}")
    private String USER_NAME;

    @Value("${app.datasource.password}")
    private String DB_PASSWORD;

    @Value("${app.datasource.driver}")
    private String JDBC_DRIVER_NAME;

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
