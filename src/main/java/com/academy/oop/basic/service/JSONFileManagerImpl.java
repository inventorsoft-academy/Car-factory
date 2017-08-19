package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.PartsType;
import com.academy.oop.basic.util.FileCleaner;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONFileManagerImpl implements FileManager {
	private static final Logger log = Logger.getLogger(JSONFileManagerImpl.class);

	private static final String PARTS_FILE_PATH = "src\\main\\resources\\JSONFiles\\Parts.json";
	private static final String CARS_FILE_PATH = "src\\main\\resources\\JSONFiles\\Cars.json";

	private static List<Part> parts;
	private static List<Car> cars;


	@Override
	public List<Part> getPartList() {
		if (parts != null) {
			log.info("return exist parts list");
			return parts;
		} else {
			parts = new ArrayList<>();
			try {
				Scanner scn = new Scanner(new File(PARTS_FILE_PATH));
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(scn.nextLine());
				JSONObject obj;
				for (int i = 0; i < array.size(); i++) {
					obj = (JSONObject) parser.parse(array.get(i).toString());
					parts.add(new Part((String) obj.get("name"), PartsType.valueOf((String) obj.get("type")),
							(double) obj.get("price"), Integer.parseInt("" + obj.get("id"))));
				}
			} catch (ParseException | FileNotFoundException e) {
				e.printStackTrace();
			}
			return parts;
		}
	}

	@Override
	public List<Car> getCarList() {
		if (cars != null) {
			log.info("return exist car list");
			return cars;
		} else {
			cars = new ArrayList<>();
			try {
				Scanner scn = new Scanner(new File(CARS_FILE_PATH));
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(scn.nextLine());
				JSONObject obj;
				for (int i = 0; i < array.size(); i++) {
					obj = (JSONObject) parser.parse(array.get(i).toString());
					cars.add(new Car((String) obj.get("brand"), (String) obj.get("model"),
							Integer.parseInt(obj.get("createdDate") + ""), (String) obj.get("color"),
							(double) obj.get("price"), Integer.parseInt(obj.get("id") + "")));
				}
			} catch (ParseException | FileNotFoundException e) {
				e.printStackTrace();
			}
			return cars;
		}
	}

	@Override
	public int getNextId(List list) {
		return list.size();
	}

	@Override
	public void refreshCarFile() {
		if (cars != null) {
			saveCars(cars);
		}
	}

	@Override
	public void refreshPartFile() {
		if (parts != null) {
			saveParts(parts);
		}
	}

	private void saveCars(List<Car> cars) {
		File file = new File(CARS_FILE_PATH);
		FileCleaner.crear(file);
		JSONObject obj;
		JSONArray array = new JSONArray();
		for (Car c : cars) {
			obj = new JSONObject();
			obj.put("brand", c.getBrand());
			obj.put("model", c.getModel());
			obj.put("createdDate", c.getCreatedDate());
			obj.put("color", c.getColor());
			obj.put("price", c.getPrice());
			obj.put("id", c.getCarId());
			array.add(obj);
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.append(array.toJSONString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveParts(List<Part> parts) {
		File file = new File(PARTS_FILE_PATH);
		FileCleaner.crear(file);
		JSONObject obj;
		JSONArray array = new JSONArray();
		for (Part p : parts) {
			obj = new JSONObject();
			obj.put("name", p.getName());
			obj.put("type", p.getType().toString());
			obj.put("price", p.getPrice());
			obj.put("id", p.getPartId());
			array.add(obj.toJSONString());
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.append(array.toJSONString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
