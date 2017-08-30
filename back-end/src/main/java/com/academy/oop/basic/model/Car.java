package com.academy.oop.basic.model;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Validator {

	private static AtomicInteger uniqueId = new AtomicInteger();

	private int carId;

	private String brand;

	private String model;

	private List<Part> parts;

	private LocalDate createdDate;

	private String color;

	private Double price;

	public Car() {
		this.carId = uniqueId.getAndIncrement();
		this.createdDate = LocalDate.now();
		this.parts = Arrays.asList(
				new Part("default engine", PartsType.ENGINE, 1.0),
				new Part("default suspension", PartsType.SUSPENSION, 1.0),
				new Part("default steering", PartsType.STEERING, 1.0)
		);
	}

	public Car(String brand, String model, String color) {
		this.brand = brand;
		this.model = model;
		this.createdDate = LocalDate.now();
		this.color = color;
		this.price = 0.0;
		this.carId = uniqueId.getAndIncrement();
		this.parts = Arrays.asList(
				new Part("default engine", PartsType.ENGINE, 1.0),
				new Part("default suspension", PartsType.SUSPENSION, 1.0),
				new Part("default steering", PartsType.STEERING, 1.0)
		);

	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public LocalDate getCreatedDate() {
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
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		if (carId != car.carId) return false;
		if (uniqueId != null ? !uniqueId.equals(car.uniqueId) : car.uniqueId != null) return false;
		if (!brand.equals(car.brand)) return false;
		if (!model.equals(car.model)) return false;
		if (parts != null ? !parts.equals(car.parts) : car.parts != null) return false;
		if (!createdDate.equals(car.createdDate)) return false;
		if (color != null ? !color.equals(car.color) : car.color != null) return false;
		return price.equals(car.price);
	}

	@Override
	public int hashCode() {
		int result = uniqueId != null ? uniqueId.hashCode() : 0;
		result = 31 * result + carId;
		result = 31 * result + brand.hashCode();
		result = 31 * result + model.hashCode();
		result = 31 * result + (parts != null ? parts.hashCode() : 0);
		result = 31 * result + createdDate.hashCode();
		result = 31 * result + (color != null ? color.hashCode() : 0);
		result = 31 * result + price.hashCode();
		return result;
	}
}
