package com.academy.oop.basic.dao.impl;

import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.impl.Logger;
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

        List<Part> parts = new ArrayList<>();

        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);

            ResultSet resultSet = statement.executeQuery(GET_PARTS);

            parts = new ArrayList<>();

            while (resultSet.next()) {
                Part part = new Part();
                part.setPartId((Integer) resultSet.getObject(1));
                part.setName((String) resultSet.getObject(2));
                part.setPrice((Double) resultSet.getObject("price"));
                part.setUsed((Boolean) resultSet.getObject(4));
                parts.add(part);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return parts;
    }

    @Override
    public Part getPartById(int id) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Class.forName(JDBC_DRIVER_NAME);



            ResultSet resultSet = statement.executeQuery("SELECT _id, type, price, used FROM public.parts WHERE _id = " + id + ";");

            System.out.println(resultSet.getMetaData().getColumnCount());



            if(resultSet.first()) {
                int partId = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                double price = resultSet.getDouble("price");
                boolean used = resultSet.getBoolean("used");
                Part part = new Part();
                part.setPartId( partId);
                part.setName( name);
                part.setType( Enum.valueOf(PartsType.class, type));
                part.setPrice( price);
                part.setUsed( used);
                return part;
            }



        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return null;
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

