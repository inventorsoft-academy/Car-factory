package com.academy.oop.basic.dao.impl;

import com.academy.oop.basic.dao.CarDao;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Car;
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
public class CarDaoImpl implements CarDao {

    private final ConnectionManager connectionManager;

    @Autowired
    public CarDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean createCar(Car car) throws SQLException {
        String addCar = "INSERT INTO cars(brand, model, color, price) VALUES (?, ?, ?, ?);";

        PreparedStatement statement = connectionManager.getPreparedStatement(addCar);

        statement.setString(1, car.getBrand());
        statement.setString(2, car.getModel());
        statement.setString(3, car.getColor());
        statement.setDouble(4, car.getPrice());

        statement.execute();
        return true;
    }

    @Override
    public List<Car> getCarsList() throws SQLException {
        List<Car> cars = new ArrayList<>();

        String getCars = "SELECT * FROM cars;";
        ResultSet resultSet = connectionManager.getStatement().executeQuery(getCars);

        while (resultSet.next()) {
            Car car = new Car();
            car.setCarId(resultSet.getInt(1));
            car.setBrand(resultSet.getString(2));
            car.setModel(resultSet.getString(3));
            car.setColor(resultSet.getString(4));
            car.setPrice(resultSet.getDouble(5));
            cars.add(car);
        }

        return cars;
    }

    @Override
    public Car getCarById(int id) {
        return null;
    }

    @Override
    public boolean deleteCarById(int id) {
        return false;
    }

    @Override
    public boolean updateCar(int id, Car car) {
        return false;
    }
}
