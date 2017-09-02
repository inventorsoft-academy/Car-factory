package com.academy.oop.basic.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface ConnectionManager {

    PreparedStatement getPreparedStatement(String sql) throws SQLException;

    Statement getStatement() throws SQLException;

    void getConnection() throws ClassNotFoundException, SQLException;

    void closeConnection() throws SQLException;
}
