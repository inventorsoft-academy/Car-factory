package com.academy.oop.basic.model;

import com.academy.oop.basic.util.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Car implements Validator {

	private String brand;

	private String model;

	private int createdDate;

	private String color;

	private Double price;

	private int carId;

	public Car() {
	}

	public Car(String brand, String model, int createdDate, String color, Double price, int carId) {
		this.brand = brand;
		this.model = model;
		this.createdDate = createdDate;
		this.color = color;
		this.price = price;
		this.carId = carId;

	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getCreatedDate() {
		return createdDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}


	@Override
	public ArrayList<String> validate() {
		ArrayList<String> valid = new ArrayList<>();
		if (brand == null || brand.length() < 3 || brand.length() > 30) {
			valid.add("brand");
		}
		if (model == null || model.length() < 3 || model.length() > 30) {
			valid.add("model");
		}
		if (createdDate < 1900 || createdDate > LocalDateTime.now().getYear()) {
			valid.add("created Date");
		}
		if (color == null || color.length() < 3 || color.length() > 30) {
			valid.add("color");
		}
		if (price < 0 || price > 100000000) {
			valid.add("price");
		}
		return valid;
	}
}
