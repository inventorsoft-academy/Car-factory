package com.academy.oop.basic.service;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.impl.CarServiceImpl;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarServiceTest {

    private CarService carService;
    private PartService partService;

    @Before
    public void setUp() throws Exception {

        carService = new CarServiceImpl(partService);

    }

    @Test
    public void createCar() throws Exception {

        List<Part> firstParts = Arrays.asList(
                new Part(PartsType.STEERING, 1.0,false),
                new Part(PartsType.STEERING, 1.0,false),
                new Part(PartsType.STEERING, 1.0,false),
                new Part(PartsType.STEERING, 1.0,false),
                new Part(PartsType.STEERING, 1.0,false)
        );

        partService.addParts(firstParts);

        Car car = new Car("brand", "model", "color");
        carService.createCar(car);

        assertEquals(car, carService.getCarById(car.getCarId()));
    }

    @Test
    public void getCarsList() throws Exception {
    }

}