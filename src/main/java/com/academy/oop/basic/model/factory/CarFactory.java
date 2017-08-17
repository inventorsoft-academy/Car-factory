package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Car;

import java.util.List;

public interface CarFactory {

	boolean createCar(String brand, String model, String color) throws Exception;

	List<Car> getCarsList();

}
