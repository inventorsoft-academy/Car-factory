package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;

import java.util.List;

public interface IPartsStorage {
	void save(Part part);

	void update(int partID, Part newPart);

	void remove(int partID);

	Part getByType(PartsType type);

	List<Part> getParts();
}
