package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartService {

	List<Part> getParts();

	Part getPartById(int id);

	boolean addPart(Part part) throws SQLException, ClassNotFoundException;

	boolean deletePartById(int id);

    boolean updatePart(int id, Part part);
}
