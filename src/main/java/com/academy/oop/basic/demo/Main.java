package com.academy.oop.basic.demo;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.*;
import com.academy.oop.basic.service.FileManager;
import com.academy.oop.basic.service.JSONFileManagerImpl;
import com.academy.oop.basic.util.Logger;

import java.util.Scanner;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	private PartsStorage partsStorage = new PartsStorageImpl();
	private CarFactory carFactory = new CarFactoryImpl();
	private Scanner in = new Scanner(System.in);
	//private FileManager fileManager = new FileManagerImpl();
	private FileManager fileManager = new JSONFileManagerImpl();

	public static void main(String[] args) {
		Main main = new Main();
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("\n1 - Create part\n2 - Create car\n3 - Show parts list\n4 - Show Car list\n5 - Exit\nMake a choice:");
			switch (in.next()) {
				case "1":
					main.createPart();
					log.info("Part created!");
					break;
				case "2":
					main.createCar();
					log.info("Car created!");
					break;
				case "3":
					main.showPartList();
					log.info("show parts list");
					break;
				case "4":
					main.showCarList();
					log.info("show cars list");
					break;
				case "5":
					log.info("exit");
					main.fileManager.refreshPartFile();
					main.fileManager.refreshCarFile();
					flag = false;
					break;
				default:
					log.info("incorrect");
					System.out.println("Incorrect");
			}
		}

	}

	private void createPart() {
		String partName = "";
		PartsType partsType = PartsType.ENGINE;
		Double price = 0.0;
		boolean flag = false;
		try {
			System.out.println("Enter part name:");
			partName = in.nextLine();
			System.out.println("1 - Engine\n2 - Steering\n3 - Suspension\nSelect the type of part:");
			String choice = in.nextLine();
			if (choice.equals("1")) {
				partsType = PartsType.ENGINE;
			} else if (choice.equals("2")) {
				partsType = PartsType.STEERING;
			} else if (choice.equals("3")) {
				partsType = PartsType.SUSPENSION;
			} else {
				System.out.println("Incorrect1! Will be create engine");
			}
			System.out.println("Enter price:");
			price = Double.parseDouble(in.nextLine());
			flag = true;
		} catch (Exception ex) {
			log.error("incorrect price!");
			System.out.println("Incorrectly entered data! Try again" + ex.getMessage());
		}
		if (flag) {
			try {
				partsStorage.save(new Part(partName, partsType, price, fileManager.getNextId(fileManager.getPartList())));
				System.out.println("Successfully");
			} catch (Exception e) {
				System.out.println("Fields: " + e.getMessage() + " not valid! Please try again!");
			}
		}
	}

	private void createCar() {
		CarFactory carFactory = new CarFactoryImpl();
		String brand = "";
		String model = "";
		String color = "";
		try {
			System.out.println("Enter car brand:");
			brand = in.next();
			System.out.println("Enter car model:");
			model = in.next();
			System.out.println("Enter car color:");
			color = in.next();
		} catch (Exception ex) {
			System.out.println("Incorrectly entered data! Try again");
		}
		boolean status = false;
		try {
			status = carFactory.createCar(brand, model, color);
			if (status) {
				System.out.println("Successfully");
			} else {
				System.out.println("Unsuccessfully! Do not have a parts! ");
			}
		} catch (Exception e) {
			System.out.println("Fields: " + e.getMessage() + " not valid! Please try again!");
		}
	}

	private void showPartList() {
		partsStorage.getParts().stream().forEach(part -> System.out.println(part.getPartId() + 1
				+ " - Name: " + part.getName() + ", Type: " + part.getType().toString().toLowerCase()
				+ ", Price: " + part.getPrice() + ";"));
	}

	private void showCarList() {
		carFactory.getCarsList().stream().forEach(car -> System.out.println(car.getCarId() + 1
				+ " - Brand: " + car.getBrand() + ", Model: " + car.getModel() + ", Color: " + car.getColor() + ", Price: " + car.getPrice()
				+ ", Created date: " + car.getCreatedDate() + ";"));
	}


}
