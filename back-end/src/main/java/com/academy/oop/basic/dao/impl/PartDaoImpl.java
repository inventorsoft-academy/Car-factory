package com.academy.oop.basic.dao.impl;


import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.exception.NotFoundException;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    private final ConnectionManager connectionManager;

    @Autowired
    public PartDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean addPart(Part part) throws SQLException, ClassNotFoundException {

        String addPart = "INSERT INTO parts(type, price, used) VALUES (?, ?, ?);";

        PreparedStatement statement = connectionManager.getPreparedStatement(addPart);

        String someType = String.valueOf(part.getType());
        Double price = part.getPrice();
        boolean used = part.isUsed();
        statement.setString(1, someType);
        statement.setDouble(2, price);
        statement.setBoolean(3, used);

        statement.execute();
        return true;
    }

    @Override
    public List<Part> getParts() throws SQLException {
        List<Part> parts = new ArrayList<>();

        String getParts = "SELECT * FROM parts;";
        ResultSet resultSet = connectionManager.getStatement().executeQuery(getParts);

        while (resultSet.next()) {
            Part part = new Part();
            part.setPartId((Integer) resultSet.getObject(1));
            part.setType(Enum.valueOf(PartsType.class, (String) resultSet.getObject(2)));
            part.setPrice((Double) resultSet.getObject(3));
            part.setUsed((Boolean) resultSet.getObject(4));
            parts.add(part);
        }

        return parts;
    }

    @Override
    public Part getPartById(int id) throws SQLException, NotFoundException {

        String getPartById = "SELECT * FROM parts WHERE id = ?;";
        PreparedStatement statement = connectionManager.getPreparedStatement(getPartById);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int partId = resultSet.getInt("id");
            String type = resultSet.getString("type");
            double price = resultSet.getDouble("price");
            boolean used = resultSet.getBoolean("used");
            Part part = new Part();
            part.setPartId(partId);
            part.setType(Enum.valueOf(PartsType.class, type));
            part.setPrice(price);
            part.setUsed(used);
            return part;
        }
        throw new NotFoundException();
    }

    @Override
    public boolean updatePart(int partId, Part part) throws SQLException {

            String updatePart = "UPDATE parts SET type=?, price=?, used=? WHERE id = ?;";

            PreparedStatement statement = connectionManager.getPreparedStatement(updatePart);

            statement.setInt(4, partId);
            statement.setString(1, String.valueOf(part.getType()));
            statement.setDouble(2, part.getPrice());
            statement.setBoolean(3, part.isUsed());
            statement.execute();
            return true;
    }

    @Override
    public boolean deletePartById(int id) throws SQLException {
            String deletePartById = "DELETE FROM parts WHERE id = ?;";
            PreparedStatement statement = connectionManager.getPreparedStatement(deletePartById);
            statement.setInt(1, id);
            statement.execute();
            return true;
    }
}
