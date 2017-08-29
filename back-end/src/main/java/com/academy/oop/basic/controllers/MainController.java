package com.academy.oop.basic.controllers;


import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.CarFactory;
import com.academy.oop.basic.model.factory.PartsStorage;
import com.academy.oop.basic.model.factory.PartsType;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/car-factory")
@CrossOrigin(origins = "*")
public class MainController {

	private static final Logger log = Logger.getLogger(MainController.class);

	private PartsStorage partsStorage;

	private CarFactory carFactory;

	public MainController(PartsStorage partsStorage, CarFactory carFactory) {
		this.partsStorage = partsStorage;
		this.carFactory = carFactory;
	}

	@GetMapping(value = "/parts", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Part> getParts() {
		return partsStorage.getParts();
	}

	@GetMapping(value = "/part/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Part getPartBuID(@PathVariable int id) {
		List<Part> parts = partsStorage.getParts();
		for (Part part : parts) {
			if (part.getPartId() == id) {
				return part;
			}
		}
		return null;
	}

	@GetMapping(value = "/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Car getCar(@PathVariable int id) {
		List<Car> cars = carFactory.getCarsList();
		for (Car car : cars) {
			if (car.getCarId() == id) {
				return car;
			}
		}
		return null;
	}

	@GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Car> getCars() {
		return carFactory.getCarsList();
	}

	@PostMapping(value = "/create/part", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createPart(@RequestBody JSONObject obj) {
		try {
			partsStorage.saveJSON(obj);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@PostMapping(value = "/create/car", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCar(@RequestBody JSONObject obj) {
		try {
			carFactory.createCar(obj.get("brand").toString(), obj.get("model").toString(), obj.get("color").toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PartsType[] getTypes() {
		return PartsType.values();
	}


}
