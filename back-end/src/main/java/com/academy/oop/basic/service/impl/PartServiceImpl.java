package com.academy.oop.basic.service.impl;

import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.impl.Logger;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartServiceImpl implements PartService {

	private static final Logger log = Logger.getLogger(PartServiceImpl.class);

	private SqlManager sqlManager;

	private List<Part> parts;

	public PartServiceImpl() {
		this.parts = new ArrayList<>();
	}

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
		return sqlManager.getParts();
//		return parts;
	}

	@Override
	public Part getPartById(int id) {
		return parts.stream()
				.filter(part -> part.getPartId() == id)
				.findFirst().get();
	}

	@Override
	public boolean addPart(Part part) {
		sqlManager.addPart(part);
		return parts.add(part);
	}

	@Override
	public boolean addParts(List<Part> parts) {
		return this.parts.addAll(parts);
	}

	@Override
	public boolean editPart(Part oldPart, Part newPart) {
		for (Part part : parts) {
			if (part.equals(oldPart)) {
				part.setType(newPart.getType());
				part.setPrice(newPart.getPrice());
				part.setUsed(newPart.isUsed());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deletePartById(int id) {
		return parts.remove(getPartById(id));
	}

	@Override
	public boolean deletePart(Part part) {
		return parts.remove(part);
	}

	@Override
	public void deleteParts() {
		parts.clear();
	}
}
