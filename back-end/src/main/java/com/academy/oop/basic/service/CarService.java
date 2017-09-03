package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarService {

	List<Car> getCarsList() throws SQLException;

	boolean createCar(Car car) throws SQLException, NoSuchFieldException, ClassNotFoundException;

	Car getCarById(int id);
}
