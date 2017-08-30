package com.academy.oop.basic.model;

import com.academy.oop.basic.util.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Validator {

	private AtomicInteger uniqueId = new AtomicInteger();

	private String brand;

	private String model;

	private int createdDate;

	private String color;

	private Double price;

	private int carId;

	public Car() {
		this.carId = uniqueId.getAndIncrement();
	}

	public Car(String brand, String model, int createdDate, String color, Double price) {
		this.brand = brand;
		this.model = model;
		this.createdDate = createdDate;
		this.color = color;
		this.price = price;
		this.carId = uniqueId.getAndIncrement();

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Car)) return false;

		Car car = (Car) o;

		if (getCreatedDate() != car.getCreatedDate()) return false;
		if (getCarId() != car.getCarId()) return false;
		if (uniqueId != null ? !uniqueId.equals(car.uniqueId) : car.uniqueId != null) return false;
		if (!getBrand().equals(car.getBrand())) return false;
		if (!getModel().equals(car.getModel())) return false;
		if (!getColor().equals(car.getColor())) return false;
		return getPrice().equals(car.getPrice());
	}

	@Override
	public int hashCode() {
		int result = uniqueId != null ? uniqueId.hashCode() : 0;
		result = 31 * result + getBrand().hashCode();
		result = 31 * result + getModel().hashCode();
		result = 31 * result + getCreatedDate();
		result = 31 * result + getColor().hashCode();
		result = 31 * result + getPrice().hashCode();
		result = 31 * result + getCarId();
		return result;
	}
}
