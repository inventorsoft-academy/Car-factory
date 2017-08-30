package com.academy.oop.basic.util;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;

import java.util.List;

public interface FileManager {
	List<Part> loadPartList();

	List<Car> loadCarList();

	int getNextId(List parts);

	void refreshCarFile();

	void refreshPartFile();

}
