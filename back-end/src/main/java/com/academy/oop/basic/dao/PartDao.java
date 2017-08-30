package com.academy.oop.basic.dao;

import com.academy.oop.basic.model.Part;

import java.util.List;

public interface PartDao {

    List<Part> getParts();

    Part getPartById(int id);

    boolean addPart(Part part);

    boolean deletePartById(int id);

}
