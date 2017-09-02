package com.academy.oop.basic.dao.impl;


import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    private static final String URL_PATH = "jdbc:postgresql://localhost:5432/car_factory";

    private static final String USER_NAME = "postgres";

    private static final String DB_PASSWORD = "";

    private static final String JDBC_DRIVER_NAME = "org.postgresql.Driver";

    private Logger logger = Logger.getLogger(PartDaoImpl.class);

    @Override
    public boolean addPart(Part part) throws SQLException, ClassNotFoundException {

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
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
        return false;
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

            ResultSet resultSet = statement.executeQuery("SELECT * FROM parts;");

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.parts WHERE _id = ?;");

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
    public boolean updatePart(int partId, Part part) {
        try (Connection connection = DriverManager
                .getConnection(
                        URL_PATH,
                        USER_NAME,
                        DB_PASSWORD)) {
            Class.forName(JDBC_DRIVER_NAME);
            //TODO fix _id to id
            String update = "UPDATE public.parts SET type=?, price=?, used=? WHERE _id = ?;";

            PreparedStatement statement = connection.prepareStatement(update);

            statement.setInt(4, partId);
            statement.setString(1, String.valueOf(part.getType()));
            statement.setDouble(2, part.getPrice());
            statement.setBoolean(3, part.isUsed());

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
