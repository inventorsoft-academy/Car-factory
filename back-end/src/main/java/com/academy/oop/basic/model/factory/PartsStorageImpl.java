package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.util.FileManager;
import com.academy.oop.basic.util.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PartsStorageImpl implements PartsStorage {

	private static final Logger log = Logger.getLogger(PartsStorageImpl.class);

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
		log.info("save a new part");
		Part part = new Part(obj.get("name").toString(), PartsType.valueOf(obj.get("type").toString()),
				Double.parseDouble(obj.get("price").toString()), fileManager.getNextId(fileManager.loadPartList()));
		if (part.validate().isEmpty()) {
			fileManager.loadPartList().add(part);
			fileManager.refreshPartFile();
		} else {
			String ex = part.validate().stream().collect(Collectors.joining(", "));
			log.error(ex);
			throw new Exception(ex);
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
}
