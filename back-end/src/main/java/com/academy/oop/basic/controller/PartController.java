package com.academy.oop.basic.controller;


import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.model.factory.CarFactory;
import com.academy.oop.basic.model.factory.PartsStorage;
import com.academy.oop.basic.model.factory.PartsType;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car-factory")
@CrossOrigin(origins = "*", methods = {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PUT,
		RequestMethod.OPTIONS})
public class PartController {

	private static final Logger log = Logger.getLogger(PartController.class);

	@Autowired
	private PartsStorage partsStorage;

	@Autowired
	private CarFactory carFactory;


	@GetMapping("/parts")
	public ResponseEntity<List<Part>> getParts() {
		if (partsStorage.getParts() != null) {
			return new ResponseEntity<>(partsStorage.getParts(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/part/{id}")
	public ResponseEntity<Part> getPartById(@PathVariable int id) {
		List<Part> parts = partsStorage.getParts();
		for (Part part : parts) {
			if (part.getPartId() == id) {
				return new ResponseEntity<>(part, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/create/part", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createPart(@RequestBody JSONObject obj) {
		try {
			partsStorage.saveJSON(obj);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@GetMapping(value = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
	public PartsType[] getTypes() {
		return PartsType.values();
	}


}
