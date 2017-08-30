package com.academy.oop.basic.dao.impl;

import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.impl.Logger;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresSqlManager implements SqlManager {

    private Logger logger = Logger.getLogger(PostgresSqlManager.class);
    private ResultSet resultSet;


    @Override
    public void createCars() {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            statement.executeQuery(CREATE_TABLE_CAR);

            List<List<Object>> result = new ArrayList<>();

            int columnCount = resultSet.getMetaData().getColumnCount();

            List<Object> headers = new ArrayList<>();
            for (int index = 1; index <= columnCount; index++) {
                headers.add(resultSet.getMetaData().getColumnName(index));
            }
            result.add(headers);

            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int index = 1; index <= columnCount; index++) {
                    row.add(resultSet.getObject(index));
                }
                result.add(row);
            }

            result.forEach(row -> {
                row.forEach(value -> System.out.print(value + "         "));
                System.out.println();
            });


			/*
            List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId((Integer) resultSet.getObject(1));
				user.setEmail((String) resultSet.getObject(2));
				user.setName((String) resultSet.getObject("name"));
				user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
				users.add(user);
			}
			users.forEach(System.out::println);
			*/

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void createParts() {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            statement.executeQuery(CREATE_TABLE_PART);

            List<List<Object>> result = new ArrayList<>();

            int columnCount = resultSet.getMetaData().getColumnCount();

            List<Object> headers = new ArrayList<>();
            for (int index = 1; index <= columnCount; index++) {
                headers.add(resultSet.getMetaData().getColumnName(index));
            }
            result.add(headers);

            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int index = 1; index <= columnCount; index++) {
                    row.add(resultSet.getObject(index));
                }
                result.add(row);
            }

            result.forEach(row -> {
                row.forEach(value -> System.out.print(value + "         "));
                System.out.println();
            });


			/*
            List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = new User();
				user.setId((Integer) resultSet.getObject(1));
				user.setEmail((String) resultSet.getObject(2));
				user.setName((String) resultSet.getObject("name"));
				user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
				users.add(user);
			}
			users.forEach(System.out::println);
			*/

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<Part> getParts() {

        return null;
    }
}

