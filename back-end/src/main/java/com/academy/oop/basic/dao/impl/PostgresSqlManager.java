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

            while (resultSet.next()) {
                Part part = new Part();
                part.setPartId((Integer) resultSet.getObject(1));
                part.setType(Enum.valueOf(PartsType.class, (String) resultSet.getObject(2)) );
                part.setPrice((Double) resultSet.getObject(3));
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
                        DB_PASSWORD)) {
            Class.forName(JDBC_DRIVER_NAME);

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM parts WHERE _id = ?;");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int partId = resultSet.getInt("_id");
                String type = resultSet.getString("type");
                double price = resultSet.getDouble("price");
                boolean used = resultSet.getBoolean("used");
                Part part = new Part();
                part.setPartId( partId);
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
    public boolean updatePart(int partId, Part part) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD)) {
            Class.forName(JDBC_DRIVER_NAME);

            String update = "UPDATE public.parts SET _id=?, type=?, price=?, used=? WHERE _id = ?;";


            PreparedStatement statement = connection.prepareStatement(update);

            statement.setInt(5, partId);
            statement.setInt(1, partId);
            statement.setString(2, String.valueOf(PartsType.STEERING));
            statement.setDouble(3, part.getPrice());
            statement.setBoolean(4, part.isUsed());

            statement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deletePartById(int id) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD)) {
            Class.forName(JDBC_DRIVER_NAME);



            String delete = "DELETE FROM public.parts WHERE _id = ?;";
            PreparedStatement statement = connection.prepareStatement(delete);

            statement.setInt(1, id);

            statement.execute();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return false;
    }
}

