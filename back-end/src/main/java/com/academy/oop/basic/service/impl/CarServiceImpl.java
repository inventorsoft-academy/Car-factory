package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.CarService;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.impl.Logger;
import javassist.NotFoundException;
import org.postgresql.util.PGmoney;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private static final Logger log = Logger.getLogger(CarServiceImpl.class);

    private FileManager fileManager;
    private PartService partService;
    private List<Car> cars;
    private int NUMBER_OF_MINIMUM_PARTS = 3;

    public CarServiceImpl(FileManager fileManager, PartService partService) {
        this.fileManager = fileManager;
        this.partService = partService;
        this.cars = new ArrayList<>();
    }

    public CarServiceImpl(PartService partService) {
        this.partService = partService;
        this.cars = new ArrayList<>();
    }

    @Override
    public boolean createCar(String brand, String model, String color) {
        if (!brand.isEmpty()) {
            if (NUMBER_OF_MINIMUM_PARTS > partService.getParts().size()) {
                try {
                    throw new NotFoundException("Not enough parts for your car");
                } catch (NotFoundException e) {
                    log.error(e.getMessage());
                    return false;
                }
            }

            Car car = new Car(brand, model, color);

            cars.add(countPrice(car));
            return true;
        }
        return false;
    }

    private void deleteUsedParts() {
        List<Part> tempParts = partService.getParts().stream().filter(p -> !p.isUsed()).collect(Collectors.toList());
        partService.deleteParts();
        partService.addParts(tempParts);
    }

    @Override
    public List<Car> getCarsList() {
        return cars;
    }

    @Override
    public boolean createCar(Car car) {
        if (NUMBER_OF_MINIMUM_PARTS > partService.getParts().size()) {
            try {
                throw new NotFoundException("Not enough parts for your car");
            } catch (NotFoundException e) {
                log.error(e.getMessage());
                return false;
            }
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

    private Car countPrice(Car car) {
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
