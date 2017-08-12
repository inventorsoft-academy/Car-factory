package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarFactory implements ICarFactory {

	@Override
	public boolean createCar(String brand, String model, String color) {
		boolean status = true;
		PartsStorage partsStorage = new PartsStorage();
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
			FileManager.getCarList().add(car);
			return status;
		} else {
			return status;
		}
	}

	@Override
	public List<Car> getCarsList() {
		return FileManager.getCarList();
	}
}
