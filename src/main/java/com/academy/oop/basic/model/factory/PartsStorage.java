package com.academy.oop.basic.model.factory;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.FileManager;

import java.util.List;

public class PartsStorage implements IPartsStorage {

	@Override
	public void save(Part part) {
		FileManager.getPartList().add(part);
	}

	@Override
	public void update(int partID, Part newPart) {
		remove(partID);
		save(newPart);
	}

	@Override
	public void remove(int partID) {
		List<Part> parts = FileManager.getPartList();
		for (Part p : parts) {
			if (p.getPartId() == partID) ;
			parts.remove(p);
			break;
		}
	}

	@Override
	public Part getByType(PartsType type) {
		List<Part> parts = FileManager.getPartList();
		for (Part p : parts) {
			if (p.getType() == type) {
				remove(p.getPartId());
				return p;
			}
		}
		return null;
	}

	@Override
	public List<Part> getParts() {
		return FileManager.getPartList();
	}

	public static int getNextId() {
		return FileManager.getNextId(FileManager.getPartList());
	}
}
