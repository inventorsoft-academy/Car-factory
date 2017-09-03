package com.academy.oop.basic.dao;

import com.academy.oop.basic.model.Car;
import java.util.List;

public interface CarDao {

    List<Car> getCarsList();

    boolean createCar(Car car);

    Car getCarById(int id);

    boolean deleteCarById(int id);

    boolean updateCar(int id, Car car);


}
