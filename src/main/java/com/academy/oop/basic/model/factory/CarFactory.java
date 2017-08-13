package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.demo.Main;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarFactory implements ICarFactory {

	private static final Logger log = Logger.getLogger(Main.class);

	@Override
	public boolean createCar(String brand, String model, String color) {
		log.info("trying create a car");
		boolean status = true;
		PartsStorage partsStorage = new PartsStorage();
		List<Car> cars = FileManager.getCarList();
		List<Part> parts = new ArrayList<>();
		parts.add(partsStorage.getByType(PartsType.ENGINE));
		parts.add(partsStorage.getByType(PartsType.STEERING));
		parts.add(partsStorage.getByType(PartsType.SUSPENSION));

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
			Car car = new Car(brand, model, LocalDateTime.now().getYear(), color, price,
					FileManager.getNextId(getCarsList()));
			cars.add(car);
			log.info("car created!");
			return true;
		} else {
			log.info("car not created!");
			return false;
		}
	}

	@Override
	public List<Car> getCarsList() {
		return FileManager.getCarList();
	}
}
