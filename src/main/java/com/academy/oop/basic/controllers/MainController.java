package com.academy.oop.basic.controllers;


import com.academy.oop.basic.model.Car;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.CarFactory;
import com.academy.oop.basic.model.factory.PartsStorage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/car-factory")
public class MainController {

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

	@GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Car> getCars() {
		return carFactory.getCarsList();
	}

	@GetMapping(value = "/part")
	public ModelAndView createPartModel(ModelAndView model) {
		model.addObject("part", new Part());
		model.setViewName("test");
		return model;
	}

	@PostMapping(value = "/create/part")
	public ModelAndView createPert(@ModelAttribute("part") Part part, ModelAndView modelAndView) {
		try {
			partsStorage.save(part);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("test2");
		return modelAndView;
	}

}
