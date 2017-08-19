package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;

import java.util.List;

public interface PartsStorage {
	void save(Part part) throws Exception;

	void update(Part part, Part newPart) throws Exception;

	void remove(Part part);

	Part getByType(PartsType type);

	List<Part> getParts();
}
