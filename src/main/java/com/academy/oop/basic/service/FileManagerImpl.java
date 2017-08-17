package com.academy.oop.basic.service;

import com.academy.oop.basic.demo.Main;
import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.PartsType;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {
	private static final Logger log = Logger.getLogger(Main.class);

	private static final String PARTS_FILE_PATH = "src\\main\\resources\\customFiles\\Parts.txt";
	private static final String CARS_FILE_PATH = "src\\main\\resources\\customFiles\\Cars.txt";

	private static List<Part> parts;
	private static List<Car> cars;

	public List<Part> getPartList() {
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

	public List<Car> getCarList() {
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
			clearFile(file);
			for (Car car : cars) {
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
			clearFile(file);
			for (Part part : parts) {
				StringBuilder builder = new StringBuilder();
				builder.append(part.getPartId())
						.append("/")
						.append(part.getName())
						.append("/")
						.append(part.getPrice())
						.append("/")
						.append(part.getType());
				writer.append(builder.toString());
				writer.newLine();
				writer.flush();
			}
			log.info("part list saved");
		} catch (IOException ex) {
			log.error(ex.getMessage());
			System.out.println(ex.getMessage());
		}
	}

	public int getNextId(List parts) {
		return parts.size();
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

	public void clearFile(File file) {
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print("");
			log.info("file: " + file.getName() + " clean");
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
