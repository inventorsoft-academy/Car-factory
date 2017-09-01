package com.academy.oop.basic.dao;

import com.academy.oop.basic.dao.impl.PartDaoImpl;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PartDaoTest {

    @Autowired
    private PartDao partDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getParts() throws Exception {
    }

    @Test
    public void getPartById() throws Exception {
    }

    @Test
    public void addPart() throws Exception {
        Part part = new Part(PartsType.ENGINE, 123.555, false);
        partDao.addPart(part);
    }

    @Test
    public void deletePartById() throws Exception {
    }

}