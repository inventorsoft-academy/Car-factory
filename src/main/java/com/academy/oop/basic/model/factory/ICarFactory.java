package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Car;

import java.util.List;

public interface ICarFactory {

	boolean createCar(String brand, String model, String color);

	List<Car> getCarsList();

}
