package com.academy.oop.basic.dao.impl;


import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    private static final String URL_PATH = "jdbc:postgresql://localhost:5432/car_factory";

    private static final String USER_NAME = "postgres";

    private static final String DB_PASSWORD = "";

    private static final String JDBC_DRIVER_NAME = "org.postgresql.Driver";

    private Logger logger = Logger.getLogger(PartDaoImpl.class);

    @Override
    public List<Part> getParts() {
        return null;
    }

    @Override
    public Part getPartById(int id) {
        return null;
    }

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
    public boolean deletePartById(int id) {
        return false;
    }
}
