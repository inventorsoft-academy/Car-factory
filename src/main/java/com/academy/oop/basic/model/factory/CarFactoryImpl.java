package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;
import com.academy.oop.basic.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarFactoryImpl implements CarFactory {

	private static final Logger log = Logger.getLogger(CarFactoryImpl.class);

	@Autowired
	private FileManager fileManager;

	@Override
	public boolean createCar(String brand, String model, String color) throws Exception {
		log.info("trying create a car");
		PartsStorage partsStorage = new PartsStorageImpl();
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
		Car car = new Car(brand, model, LocalDateTime.now().getYear(), color, price,
				fileManager.getNextId(getCarsList()));
		if (car.validate().isEmpty()) {
			fileManager.getCarList().add(car);
			parts.forEach(partsStorage::remove);
			log.info("car created!");
			return true;
		} else {
			log.info("car not created!");
			throw new Exception(car.validate().stream().collect(Collectors.joining(", ")));
		}
	}

	@Override
	public List<Car> getCarsList() {
		return fileManager.getCarList();
	}
}
