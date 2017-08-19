package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.demo.Main;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;
import com.academy.oop.basic.service.JSONFileManagerImpl;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class PartsStorageImpl implements PartsStorage {

	private static final Logger log = Logger.getLogger(Main.class);
	//private FileManager fileManager = new FileManagerImpl();
	private FileManager fileManager = new JSONFileManagerImpl();

	@Override
	public void save(Part part) throws Exception {
		log.info("save a new part");
		if (part.validate().isEmpty()) {
			fileManager.getPartList().add(part);
		} else {
			throw new Exception(part.validate().stream().collect(Collectors.joining(", ")));
		}
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
		fileManager.getPartList().remove(part);
	}

	@Override
	public Part getByType(PartsType type) {
		log.info("trying get part by type");
		List<Part> parts = fileManager.getPartList();
		for (Part p : parts) {
			if (p.getType() == type) {
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
		return fileManager.getPartList();
	}
}
