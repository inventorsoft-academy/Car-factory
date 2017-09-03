package com.academy.oop.basic.controller;


import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/car-factory/parts")
@CrossOrigin(origins = "*", methods = {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PUT,
		RequestMethod.OPTIONS})
public class PartController {

	private Logger logger = Logger.getLogger(PartController.class);

	@Autowired
	private PartService partService;

	@PostMapping
	public ResponseEntity<Part> createPart(@RequestBody Part part) throws SQLException, ClassNotFoundException {
		if (partService.addPart(part)) {
			return new ResponseEntity<>(part, HttpStatus.CREATED);
		}
		logger.error("Part + " + part.toString() + " was not created");
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{id}")
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Part> getPartById(@PathVariable int id) throws SQLException {
		if (partService.getPartById(id) != null) {
			return new ResponseEntity<>(partService.getPartById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Part>> getParts() throws SQLException {
		if (partService.getParts() != null) {
			return new ResponseEntity<>(partService.getParts(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Part> updatePart(@PathVariable int id, @RequestBody Part part) throws SQLException {
		if (partService.updatePart(id, part)) {
			return new ResponseEntity<>(part, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePartById(@PathVariable int id) throws SQLException {
		if (partService.deletePartById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@GetMapping("/types")
	public ResponseEntity<PartsType[]> getTypes() {
		if (PartsType.values().length > 1) {
			return new ResponseEntity<>(PartsType.values(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
