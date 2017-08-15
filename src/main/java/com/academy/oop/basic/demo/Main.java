package com.academy.oop.basic.demo;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.*;
import com.academy.oop.basic.service.FileManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	private IPartsStorage partsStorage = new PartsStorage();
	private ICarFactory carFactory = new CarFactory();
	private Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("\n1 - Create part\n2 - Create car\n3 - Show parts list\n4 - Show Car list\n5 - Exit\nMake a choice:");
			switch (in.nextInt()) {
				case 1:
					main.createPart();
					log.info("Part created!");
					break;
				case 2:
					main.createCar();
					log.info("Car created!");
					break;
				case 3:
					main.showPartList();
					log.info("show parts list");
					break;
				case 4:
					main.showCarList();
					log.info("show cars list");
					break;
				case 5:
					log.info("exit");
					FileManager.refreshPartFile();
					FileManager.refreshCarFile();
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
			partName = in.next();
			System.out.println("1 - Engine\n2 - Steering\n3 - Suspension\nSelect the type of part:");
			partsType = PartsType.ENGINE;
			int choice = in.nextInt();
			if (choice == 1) {
				partsType = PartsType.ENGINE;
			} else if (choice == 2) {
				partsType = PartsType.STEERING;
			} else if (choice == 3) {
				partsType = PartsType.SUSPENSION;
			} else {
				System.out.println("Incorrect1! Will be create engine");
			}
			System.out.println("Enter price:");
			price = in.nextDouble();
			flag = true;
		} catch (Exception ex) {
			System.out.println("Incorrectly entered data! Try again");
		}
		if (flag) {
			try {
				partsStorage.save(new Part(partName, partsType, price, PartsStorage.getNextId()));
				System.out.println("Successfully");
			} catch (Exception e) {
				System.out.println("Fields: " + e.getMessage() + " not valid! Please try again!");
			}
		}
	}

	private void createCar() {
		CarFactory carFactory = new CarFactory();
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
		for (Part p : partsStorage.getParts()) {
			System.out.println(p.getName());
		}
	}

	private void showCarList() {
		for (Car car : carFactory.getCarsList()) {
			System.out.println(car.getModel());
		}
	}


}
