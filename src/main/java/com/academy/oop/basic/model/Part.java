package com.academy.oop.basic.model;

import com.academy.oop.basic.model.factory.PartsType;
import com.academy.oop.basic.service.Validator;

import java.util.ArrayList;

public class Part implements Validator {

	private String name;

	private PartsType type;

	private Double price;

	private int partId = 0;

	public Part(String name, PartsType type, Double price, int partId) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.partId = partId;
	}

	public Part() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartsType getType() {
		return type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	@Override
	public ArrayList<String> validate() {

		ArrayList<String> valid = new ArrayList<>();
		if (name == null || name.length() < 3 || name.length() > 30) {
			valid.add("name");
		}
		if (price < 0 || price > 100000000) {
			valid.add("price");
		}
		return valid;
	}
}
