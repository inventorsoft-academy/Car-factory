package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;

import java.util.List;

public interface IPartsStorage {
	void save(Part part) throws Exception;

	void update(int partID, Part newPart) throws Exception;

	void remove(int partID);

	Part getByType(PartsType type);

	List<Part> getParts();
}
