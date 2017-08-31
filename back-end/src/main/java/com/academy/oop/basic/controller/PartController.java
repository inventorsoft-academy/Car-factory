package com.academy.oop.basic.controller;


import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.dao.impl.PostgresSqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private SqlManager sqlManager;

	@PostMapping
	public void createPart(@RequestBody Part part) {
		sqlManager.addPart(part);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Part> getPartById(@PathVariable int id) {
		if (sqlManager.getPartById(id) != null) {
			return new ResponseEntity<>(sqlManager.getPartById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Part>> getParts() {
		if (sqlManager.getParts() != null) {
			return new ResponseEntity<>(sqlManager.getParts(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Part> deletePartById(@PathVariable int id) {
		sqlManager.deletePartById(id);
		return new ResponseEntity<Part>(HttpStatus.OK);
	}


	@GetMapping("/types")
	public ResponseEntity<PartsType[]> getTypes() {
		if (PartsType.values().length > 1) {
			return new ResponseEntity<>(PartsType.values(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
