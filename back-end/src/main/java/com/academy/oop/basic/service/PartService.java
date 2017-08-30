package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.enums.PartsType;
import org.json.simple.JSONObject;

import java.util.List;

public interface PartService {

	void saveJSON(JSONObject obj) throws Exception;

	void save(Part part) throws Exception;

	void update(Part part, Part newPart) throws Exception;

	void remove(Part part);

	Part getByType(PartsType type);

	List<Part> getParts();

	Part getPartById(int id);

	boolean addPart(Part part);

	boolean addParts(List<Part> parts);

	boolean editPart(Part oldPart, Part newPart);

	boolean deletePartById(int id);

}
