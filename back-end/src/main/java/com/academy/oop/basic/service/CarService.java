package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarService {

	boolean createCar(String brand, String model, String color) throws Exception;

	List<Car> getCarsList();

	boolean createCar(Car car) throws SQLException;

	Car getCarById(int id);
}
