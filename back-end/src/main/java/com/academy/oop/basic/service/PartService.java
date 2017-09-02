package com.academy.oop.basic.service;

import com.academy.oop.basic.model.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartService {

	List<Part> getParts() throws SQLException;

	Part getPartById(int id) throws SQLException;

	boolean addPart(Part part) throws SQLException, ClassNotFoundException;

	boolean addParts(List<Part> parts) throws SQLException, ClassNotFoundException;

	boolean deletePartById(int id) throws SQLException;

    boolean updatePart(int id, Part part) throws SQLException;
}
