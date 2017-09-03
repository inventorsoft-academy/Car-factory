package com.academy.oop.basic.dao;

import com.academy.oop.basic.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {

    List<Car> getCarsList() throws SQLException;

    boolean createCar(Car car) throws SQLException;

    Car getCarById(int id);

    boolean deleteCarById(int id);

    boolean updateCar(int id, Car car);


}
