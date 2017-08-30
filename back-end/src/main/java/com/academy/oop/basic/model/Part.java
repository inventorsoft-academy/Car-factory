package com.academy.oop.basic.model;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.util.Validator;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Part implements Validator {

	private static AtomicInteger uniqueId = new AtomicInteger();

	private String name;

	private PartsType type;

	private Double price;

	private int partId;

	private boolean used = false;

	public Part(String name, PartsType type, Double price) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.partId = uniqueId.getAndIncrement();
	}

	public Part() {
		this.partId = uniqueId.getAndIncrement();
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

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Part)) return false;

		Part part = (Part) o;

		if (getPartId() != part.getPartId()) return false;
		if (isUsed() != part.isUsed()) return false;
		if (uniqueId != null ? !uniqueId.equals(part.uniqueId) : part.uniqueId != null) return false;
		if (!getName().equals(part.getName())) return false;
		if (getType() != part.getType()) return false;
		return getPrice().equals(part.getPrice());
	}

	@Override
	public int hashCode() {
		int result = uniqueId != null ? uniqueId.hashCode() : 0;
		result = 31 * result + getName().hashCode();
		result = 31 * result + getType().hashCode();
		result = 31 * result + getPrice().hashCode();
		result = 31 * result + getPartId();
		result = 31 * result + (isUsed() ? 1 : 0);
		return result;
	}
}
