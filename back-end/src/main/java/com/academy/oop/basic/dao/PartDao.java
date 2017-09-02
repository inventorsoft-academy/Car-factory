package com.academy.oop.basic.dao;

import com.academy.oop.basic.model.Part;

import java.sql.SQLException;
import java.util.List;

public interface PartDao {

    List<Part> getParts() throws SQLException;

    Part getPartById(int id) throws SQLException;

    boolean addPart(Part part) throws SQLException, ClassNotFoundException;

    boolean updatePart(int partId, Part part) throws SQLException;

    boolean deletePartById(int id) throws SQLException;

}
