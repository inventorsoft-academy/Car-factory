package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.exception.NotEnoughPartsException;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.CarService;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService {

    private static final Logger log = Logger.getLogger(CarServiceImpl.class);

    private PartService partService;
    private List<Car> cars;
    private int NUMBER_OF_MINIMUM_PARTS = 3;

    public CarServiceImpl(PartService partService) {
        this.partService = partService;
        this.cars = new ArrayList<>();
    }

    @Override
    public boolean createCar(String brand, String model, String color) throws SQLException, ClassNotFoundException {
        if (!brand.isEmpty()) {
            if (NUMBER_OF_MINIMUM_PARTS > partService.getParts().size()) {
                throw new NotEnoughPartsException("Not enough parts.");
            }

            Car car = new Car(brand, model, color);

            cars.add(countPrice(car));
            return true;
        }
        return false;
    }

    private void deleteUsedParts() throws SQLException, ClassNotFoundException {
        List<Part> tempParts = partService.getParts().stream().filter(p -> !p.isUsed()).collect(Collectors.toList());
        try {
            partService.addParts(tempParts);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ClassNotFoundException("Sql exception");
        }
    }

    @Override
    public List<Car> getCarsList() {
        return cars;
    }

    @Override
    public boolean createCar(Car car) throws SQLException, NotEnoughPartsException, ClassNotFoundException {
        if (NUMBER_OF_MINIMUM_PARTS > partService.getParts().size()) {
            throw new NotEnoughPartsException("");
        }
        Car carWithPrice = countPrice(car);
        cars.add(carWithPrice);
        return true;
    }

    @Override
    public Car getCarById(int id) {
        return cars.stream()
                .filter(part -> part.getCarId() == id)
                .findFirst().get();
    }

    private Car countPrice(Car car) throws SQLException, ClassNotFoundException {
        List<Part> serviceParts = partService.getParts();

        Double carPrice = 0.0;

        List<Part> carParts = car.getParts();
        for (Part carPart : carParts) {
            for (Part servicePart : serviceParts) {
                if (carPart.getType().equals(servicePart.getType()) && !carPart.isUsed()) {
                    carPart.setUsed(true);
                    servicePart.setUsed(true);
                    carPart.setPrice(servicePart.getPrice());
                    carPrice += servicePart.getPrice();
                }
            }
        }
        deleteUsedParts();
        car.setPrice(carPrice);
        return car;
    }
}
