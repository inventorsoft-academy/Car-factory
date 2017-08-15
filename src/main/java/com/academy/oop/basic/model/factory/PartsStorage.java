package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.demo.Main;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class PartsStorage implements IPartsStorage {

	private static final Logger log = Logger.getLogger(Main.class);

	@Override
	public void save(Part part) throws Exception {
		log.info("save a new part");
		if (part.validate().isEmpty()) {
			FileManager.getPartList().add(part);
		} else {
			throw new Exception(part.validate().stream().collect(Collectors.joining(", ")));
		}

	}

	@Override
	public void update(int partID, Part newPart) throws Exception {
		log.info("update part");
		remove(partID);
		save(newPart);
	}

	@Override
	public void remove(int partID) {
		log.info("remove part");
		List<Part> parts = FileManager.getPartList();
		for (Part p : parts) {
			if (p.getPartId() == partID) ;
			parts.remove(p);
			break;
		}
	}

	@Override
	public Part getByType(PartsType type) {
		log.info("trying get part by type");
		List<Part> parts = FileManager.getPartList();
		for (Part p : parts) {
			if (p.getType() == type) {
				parts.remove(p);
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
		return FileManager.getPartList();
	}

	public static int getNextId() {
		return FileManager.getNextId(FileManager.getPartList());
	}
}
