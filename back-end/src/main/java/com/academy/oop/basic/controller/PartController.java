package com.academy.oop.basic.controller;


import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
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

	@Autowired
	private PartService partService;

	@PostMapping
	public void createPart(@RequestBody Part part) throws SQLException, ClassNotFoundException {
		partService.addPart(part);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Part> getPartById(@PathVariable int id) {
		if (partService.getPartById(id) != null) {
			return new ResponseEntity<>(partService.getPartById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Part>> getParts() {
		if (partService.getParts() != null) {
			return new ResponseEntity<>(partService.getParts(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Part> updatePart(@PathVariable int id, @RequestBody Part part) {
		if (partService.updatePart(id, part)) {
			return new ResponseEntity<>(part, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePartById(@PathVariable int id) {
		if (partService.deletePartById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}


	@GetMapping("/types")
	public ResponseEntity<PartsType[]> getTypes() {
		if (PartsType.values().length > 1) {
			return new ResponseEntity<>(PartsType.values(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
