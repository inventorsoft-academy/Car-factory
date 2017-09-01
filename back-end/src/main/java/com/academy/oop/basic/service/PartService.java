package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.enums.PartsType;

import java.sql.SQLException;
import java.util.List;

public interface PartService {

	List<Part> getParts();

	Part getPartById(int id);

	boolean addPart(Part part) throws SQLException, ClassNotFoundException;

	boolean addParts(List<Part> parts);

	boolean editPart(int id, Part newPart);

	boolean deletePartById(int id);

}
