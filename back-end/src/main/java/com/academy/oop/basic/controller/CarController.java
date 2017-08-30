package com.academy.oop.basic.controller;


import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.factory.CarFactory;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car-factory")
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.OPTIONS})

public class CarController {

    private static final Logger log = Logger.getLogger(CarController.class);

    @Autowired
    private CarFactory carFactory;

    @PostMapping("/create/car")
    public void createCar(@RequestBody JSONObject obj) {
        try {
            carFactory.createCar(obj.get("brand").toString(), obj.get("model").toString(), obj.get("color").toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable int id) {
        List<Car> cars = carFactory.getCarsList();
        for (Car car : cars) {
            if (car.getCarId() == id) {
                return car;
            }
        }
        return null;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carFactory.getCarsList();
    }


}
