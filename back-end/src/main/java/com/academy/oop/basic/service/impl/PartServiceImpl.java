package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartServiceImpl implements PartService {

	private static final Logger log = Logger.getLogger(PartServiceImpl.class);

	private List<Part> parts;

	public PartServiceImpl() {
		this.parts = new ArrayList<>();
	}

	@Autowired
	private FileManager fileManager;

	@Override
	public void save(Part part) throws Exception {
		log.info("save a new part");
		if (part.validate().isEmpty()) {
			fileManager.loadPartList().add(part);
		} else {
			throw new Exception(part.validate().stream().collect(Collectors.joining(", ")));
		}
	}


	public void saveJSON(JSONObject obj) throws Exception {
		log.info("method saveJSON need to impl");
	}

	@Override
	public void update(Part part, Part newPart) throws Exception {
		log.info("update part");
		remove(part);
		save(newPart);
	}

	@Override
	public void remove(Part part) {
		log.info("remove part");
		fileManager.loadPartList().remove(part);
	}

	@Override
	public Part getByType(PartsType type) {
		log.info("trying get part by type");
		List<Part> parts = fileManager.loadPartList();
		for (Part p : parts) {
			if (p.getType().equals(type)) {
				log.info("return part!");
				return p;
			}
		}
		log.error("return null!");
		return null;
	}

	@Override
	public List<Part> getParts() {
		log.info("get part list");
		return fileManager.loadPartList();
	}

	@Override
	public Part getPartById(int id) {
		return null;
	}

	@Override
	public boolean addPart(Part part) {
		return parts.add(part);
	}

	@Override
	public boolean addParts(List<Part> parts) {
		return false;
	}
}
