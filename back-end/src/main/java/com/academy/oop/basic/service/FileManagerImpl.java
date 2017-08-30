package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.PartsType;
import com.academy.oop.basic.util.FileCleaner;
import com.academy.oop.basic.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {
	private static final Logger log = Logger.getLogger(FileManagerImpl.class);

	private static final String PARTS_FILE_PATH = "src\\main\\resources\\customFiles\\Parts.txt";
	private static final String CARS_FILE_PATH = "src\\main\\resources\\customFiles\\Cars.txt";

	private static List<Part> parts;
	private static List<Car> cars;

	public List<Part> loadPartList() {
		if (parts != null) {
			log.info("return exist parts list");
			return parts;
		} else {
			parts = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(PARTS_FILE_PATH)))) {
				String line;
				String[] currentLine;
				while ((line = reader.readLine()) != null) {
					currentLine = line.split("/");
					parts.add(new Part(currentLine[1], PartsType.valueOf(currentLine[3]),
							Double.parseDouble(currentLine[2]), getNextId(parts)));
				}
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			log.info("return new parts list");
			return parts;
		}
	}

	public List<Car> loadCarList() {
		if (cars != null) {
			log.info("return exist car list");
			return cars;
		} else {
			cars = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(CARS_FILE_PATH)))) {
				String line;
				String[] currentLine;
				while ((line = reader.readLine()) != null) {
					currentLine = line.split("/");
					cars.add(new Car(currentLine[1], currentLine[2], Integer.parseInt(currentLine[3]),
							currentLine[4], Double.parseDouble(currentLine[5]), getNextId(cars)));
				}
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			log.info("return new cars list");
			return cars;
		}
	}

	private void saveCars(List<Car> cars) {
		File file = new File(CARS_FILE_PATH);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			FileCleaner.crear(file);
			for (Car car : cars) {
				String builder = String.valueOf(car.getCarId()) +
						"/" +
						car.getBrand() +
						"/" +
						car.getModel() +
						"/" +
						car.getCreatedDate() +
						"/" +
						car.getColor() +
						"/" +
						car.getPrice();
				writer.append(builder);
				writer.newLine();
				writer.flush();
			}
			log.info("car list saved");
		} catch (IOException ex) {
			log.error(ex.getMessage());
			System.out.println(ex.getMessage());
		}
	}

	private void saveParts(List<Part> parts) {
		File file = new File(PARTS_FILE_PATH);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			FileCleaner.crear(file);
			for (Part part : parts) {
				String builder = String.valueOf(part.getPartId()) +
						"/" +
						part.getName() +
						"/" +
						part.getPrice() +
						"/" +
						part.getType();
				writer.append(builder);
				writer.newLine();
				writer.flush();
			}
			log.info("part list saved");
		} catch (IOException ex) {
			log.error(ex.getMessage());
			System.out.println(ex.getMessage());
		}
	}

	public int getNextId(List list) {
		return list.size();
	}

	public void refreshCarFile() {
		if (cars != null) {
			saveCars(cars);
		}
	}

	public void refreshPartFile() {
		if (parts != null) {
			saveParts(parts);
		}
	}
}

