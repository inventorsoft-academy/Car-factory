package com.academy.oop.basic.service;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartServiceTest {

    private PartService partService;
    private Part globalPart;

    @Before
    public void setUp() throws Exception {
        partService = new PartServiceImpl();
        globalPart = new Part("detail1", PartsType.ENGINE, 123.55);
    }

    @Test
    public void addPart() throws Exception {

        assertEquals("Must be true", partService.addPart(globalPart));


    }

    @Test
    public void saveJSON() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void getByType() throws Exception {
    }

    @Test
    public void getParts() throws Exception {
    }

    @Test
    public void getPartById() throws Exception {
    }

    @Test
    public void addParts() throws Exception {
    }

}