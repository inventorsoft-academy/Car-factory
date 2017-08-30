package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.service.CarService;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

	private static final Logger log = Logger.getLogger(CarServiceImpl.class);

	private final FileManager fileManager;
	private final PartService partsStorage;

	@Autowired
	public CarServiceImpl(FileManager fileManager, PartService partsStorage) {
		this.fileManager = fileManager;
		this.partsStorage = partsStorage;
	}

	@Override
	public boolean createCar(String brand, String model, String color) throws Exception {
		log.info("trying create a car");

		List<Part> parts = new ArrayList<>();
		parts.add(partsStorage.getByType(PartsType.ENGINE));
		parts.add(partsStorage.getByType(PartsType.STEERING));
		parts.add(partsStorage.getByType(PartsType.SUSPENSION));
		Double price;
		for (Part p : parts) {
			if (p == null) {
				return false;
			}
		}
		price = parts.stream().map(Part::getPrice).reduce(0.0, Double::sum);
		Car car = new Car(brand, model, LocalDateTime.now().getYear(), color, price);
		if (car.validate().isEmpty()) {
			fileManager.loadCarList().add(car);
			parts.forEach(partsStorage::remove);
			fileManager.refreshPartFile();
			fileManager.refreshCarFile();
			log.info("car created!");
			return true;
		} else {
			log.info("car not created!");
			throw new Exception(car.validate().stream().collect(Collectors.joining(", ")));
		}
	}

	@Override
	public List<Car> getCarsList() {
		return fileManager.loadCarList();
	}
}
