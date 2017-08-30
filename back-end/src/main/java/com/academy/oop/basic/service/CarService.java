package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;

import java.util.List;

public interface CarService {

	boolean createCar(String brand, String model, String color) throws Exception;

	List<Car> getCarsList();

	boolean createCar(Car car);

	Car getCarById(int id);
}
