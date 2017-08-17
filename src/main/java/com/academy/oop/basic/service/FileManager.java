package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;

import java.io.File;
import java.util.List;

public interface FileManager {
	List<Part> getPartList();

	List<Car> getCarList();

	int getNextId(List parts);

	void refreshCarFile();

	void refreshPartFile();

	void clearFile(File file);
}
