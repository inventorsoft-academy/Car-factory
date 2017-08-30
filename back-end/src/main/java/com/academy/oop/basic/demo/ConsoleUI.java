package com.academy.oop.basic.demo;

import com.academy.oop.basic.Main;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.CarService;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI implements CommandLineRunner {
	private static Logger log = Logger.getLogger(Main.class);
	private Scanner in = new Scanner(System.in);

	private final CarService carFactory;
	private final PartService partsStorage;
	private final FileManager fileManager;


	public ConsoleUI(CarService carFactory, PartService partsStorage, FileManager fileManager) {
		this.carFactory = carFactory;
		this.partsStorage = partsStorage;
		this.fileManager = fileManager;
	}

	@Override
	public void run(String... strings) throws Exception {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("\n1 - Create part\n2 - Create car\n3 - Show parts list\n4 - Show Car list\n5 - Exit\nMake a choice:");
			switch (in.next()) {
				case "1":
					createPart();
					log.info("Part created!");
					break;
				case "2":
					createCar();
					log.info("Car created!");
					break;
				case "3":
					showPartList();
					log.info("show parts list");
					break;
				case "4":
					showCarList();
					log.info("show cars list");
					break;
				case "5":
					log.info("exit");
					fileManager.refreshPartFile();
					fileManager.refreshCarFile();
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
			String choice = in.next();
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
			price = Double.parseDouble(in.next());
			flag = true;
		} catch (Exception ex) {
			log.error("incorrect price!");
			System.out.println("Incorrectly entered data! Try again!");
		}
		if (flag) {
			try {
				partsStorage.save(new Part(partName, partsType, price));
				System.out.println("Successfully");
			} catch (Exception e) {
				System.out.println("Fields: " + e.getMessage() + " not valid! Please try again!");
			}
		}
	}

	private void createCar() {
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
