package com.academy.oop.basic.model;

import com.academy.oop.basic.model.factory.PartsType;

public class Part {

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
}
