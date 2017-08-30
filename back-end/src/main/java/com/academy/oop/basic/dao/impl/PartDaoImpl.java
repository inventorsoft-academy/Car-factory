package com.academy.oop.basic.dao.impl;


import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.dao.SqlFactory;
import com.academy.oop.basic.model.Part;

import java.util.List;

public class PartDaoImpl implements PartDao {

    private SqlFactory factory;

    @Override
    public List<Part> getParts() {
        return null;
    }

    @Override
    public Part getPartById(int id) {
        return null;
    }

    @Override
    public boolean addPart(Part part) {
        return false;
    }

    @Override
    public boolean deletePartById(int id) {
        return false;
    }
}
