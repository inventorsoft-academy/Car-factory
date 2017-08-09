package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarFactory implements ICarFactory {
	private static final String FILE_PATH = "src\\main\\resources\\Cars.txt";

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
			Car car = new Car(brand, model, LocalDateTime.now().getYear(), color, price, getNextId());
			try {
				File file = new File(FILE_PATH);
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				StringBuilder builder = new StringBuilder();
				builder.append(car.getCarId())
						.append("/")
						.append(car.getBrand())
						.append("/")
						.append(car.getModel())
						.append("/")
						.append(car.getCreatedDate())
						.append("/")
						.append(car.getColor())
						.append("/")
						.append(car.getPrice());
				writer.append(builder.toString());
				writer.newLine();
				writer.flush();
				writer.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
			return status;
		} else {
			return status;
		}
	}

	@Override
	public List<Car> getCarsList() {
		List<Car> cars = new LinkedList<>();
		File file = new File(FILE_PATH);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] currenrLine;
			while ((line = reader.readLine()) != null) {
				currenrLine = line.split("/");
				cars.add(new Car(currenrLine[1], currenrLine[2], Integer.parseInt(currenrLine[3]), currenrLine[4], Double.parseDouble(currenrLine[5]), getNextId()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cars;
	}


	public static int getNextId() {
		int carId = 0;
		try {
			File file = new File(FILE_PATH);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] currentLine;
			while ((line = reader.readLine()) != null) {
				currentLine = line.split("/");
				carId = Integer.parseInt(currentLine[0]) + 1;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return carId;
	}
}
