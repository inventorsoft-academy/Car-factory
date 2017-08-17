package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.demo.Main;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;
import com.academy.oop.basic.service.FileManagerImpl;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarFactoryImpl implements CarFactory {

	private static final Logger log = Logger.getLogger(Main.class);
	private FileManager fileManagerImpl = new FileManagerImpl();

	@Override
	public boolean createCar(String brand, String model, String color) throws Exception {
		log.info("trying create a car");
		boolean status = true;

		Car car = new Car(brand, model, LocalDateTime.now().getYear(), color, 0.0,
				fileManagerImpl.getNextId(getCarsList()));
		if (car.validate().isEmpty()) {
			PartsStorageImpl partsStorageImpl = new PartsStorageImpl();
			List<Car> cars = fileManagerImpl.getCarList();
			List<Part> parts = new ArrayList<>();
			parts.add(partsStorageImpl.getByType(PartsType.ENGINE));
			parts.add(partsStorageImpl.getByType(PartsType.STEERING));
			parts.add(partsStorageImpl.getByType(PartsType.SUSPENSION));
			for (Part p : parts) {
				if (p == null) {
					status = false;
				}
			}
			if (status) {
				Double price = 0.0;
				for (Part part : parts) {
					price += part.getPrice();
				}
				car.setPrice(price);
				cars.add(car);
				log.info("car created!");
				return true;
			} else {
				log.info("car not created!");
				return false;
			}
		} else {
			throw new Exception(car.validate().stream().collect(Collectors.joining(", ")));
		}
	}

	@Override
	public List<Car> getCarsList() {
		return fileManagerImpl.getCarList();
	}
}
