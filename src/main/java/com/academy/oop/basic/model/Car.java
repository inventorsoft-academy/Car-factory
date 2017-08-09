package com.academy.oop.basic.model;

public class Car {

	private String brand;

	private String model;

	private int createdDate;

	private String color;

	private Double price;

	private int carId;


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
}
