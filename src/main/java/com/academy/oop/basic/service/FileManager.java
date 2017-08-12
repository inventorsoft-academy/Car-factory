package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.PartsType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	private static final String PARTS_FILE_PATH = "src\\main\\resources\\Parts.txt";
	private static final String CARS_FILE_PATH = "src\\main\\resources\\Cars.txt";

	private static List<Part> parts;
	private static List<Car> cars;

	public static List<Part> getPartList() {
		if (parts != null) {
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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return parts;
		}
	}

	public static List<Car> getCarList() {
		if (cars != null) {
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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return cars;
		}
	}

	private static void saveCars(List<Car> cars) {
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
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static void saveParts(List<Part> parts) {
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
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static int getNextId(List parts) {
		return parts.size();
	}

	public static void refreshCarFile() {
		saveCars(cars);
	}

	public static void refreshPartFile() {
		saveParts(parts);
	}

	private static void clearFile(File file) {
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
