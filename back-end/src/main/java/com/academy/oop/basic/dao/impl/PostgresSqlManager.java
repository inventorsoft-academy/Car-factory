package com.academy.oop.basic.dao.impl;

import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.impl.Logger;
import org.postgresql.util.PGmoney;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostgresSqlManager implements SqlManager {

    public PostgresSqlManager() {
        createTablePart();
        createTableCar();
    }

    private Logger logger = Logger.getLogger(PostgresSqlManager.class);
    private ResultSet resultSet;

    @Override
    public void createTableCar() {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            statement.executeQuery(CREATE_TABLE_CAR);

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void createTablePart() {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);
            statement.executeQuery(CREATE_TABLE_PART);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<Part> getParts() {

        List<Part> parts = null;

        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            statement.executeQuery(GET_PARTS);

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

            parts = new ArrayList<>();

            while (resultSet.next()) {
                Part part = new Part();
                part.setPartId((Integer) resultSet.getObject(1));
                part.setName((String) resultSet.getObject(2));
                part.setPrice((Double) resultSet.getObject("price"));
                part.setUsed((Boolean) resultSet.getObject(4));
                parts.add(part);
            }
            parts.forEach(System.out::println);

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return parts;
    }

    @Override
    public Part getPartById(int id) {
        Part part = null;
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            resultSet = statement.executeQuery("SELECT _id, type, price, used FROM public.parts WHERE _id = " + id + ";");

            Object partId = resultSet.getObject(1);
            Object name = resultSet.getObject(2);
            Object type = resultSet.getObject(3);
            Object price = resultSet.getObject(4);
            Object isUsed = resultSet.getObject(5);

            part = new Part();
            part.setPartId((Integer) partId);
            part.setName((String) name);
            part.setType((PartsType) type);
            part.setPrice((Double) price);
            part.setUsed((Boolean) isUsed);

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return part;

    }

    @Override
    public void addPart(Part part) {
        try (Connection connection = DriverManager
                .getConnection(URL_PATH, USER_NAME, DB_PASSWORD)) {
            Class.forName(JDBC_DRIVER_NAME);

            String s = "INSERT INTO public.parts(type, price, used) VALUES (?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(s);

            String someType = String.valueOf(part.getType());
            Double price = part.getPrice();
            boolean used = part.isUsed();
            statement.setString(1,someType);
            statement.setDouble(2, price);
            statement.setBoolean(3, used);

            statement.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void updatePart(Part part) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            String update = "UPDATE public.parts " +
                    "SET " +
                    "_id=" + part.getPartId() + "," +
                    " type=" + part.getType() + "," +
                    " price=" + part.getPrice() + "," +
                    " used=" + part.isUsed() +
                    " WHERE _id == " + part.getPartId() + ";";

            statement.executeQuery(update);

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deletePartById(int id) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            String delete = "DELETE FROM public.parts WHERE _id=" + id + ";";

            statement.executeQuery(delete);

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}

