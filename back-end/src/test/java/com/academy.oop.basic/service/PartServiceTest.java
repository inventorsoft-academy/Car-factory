package com.academy.oop.basic.service;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartServiceTest {

    private PartService partService;
    private Part oldGlobalPart;
    private Part newGlobalPart;

    @Before
    public void setUp() throws Exception {
        partService = new PartServiceImpl();
        oldGlobalPart = new Part("old details", PartsType.STEERING, 95548.2);
        newGlobalPart = new Part("new detail", PartsType.ENGINE, 123.55);
    }

    @Test
    public void addPart() throws Exception {
        assertTrue("Part not added", partService.addPart(oldGlobalPart));
    }

    @Test
    public void getPartById() throws Exception {
        addPart();
        assertEquals("Parts not equals", oldGlobalPart, partService.getPartById(oldGlobalPart.getPartId()));
    }

    @Test
    public void editPart() throws Exception {
        partService.addPart(oldGlobalPart);
        assertTrue("part not update", partService.editPart(oldGlobalPart, newGlobalPart));
    }

    @Test
    public void deletePartById() {
        partService.addPart(oldGlobalPart);
        assertTrue("Part not deleted",partService.deletePartById(oldGlobalPart.getPartId()));
    }

    @Test
    public void getParts() throws Exception {
        List<Part> parts = Arrays.asList(
                new Part("part 1", PartsType.STEERING, 23.2),
                new Part("part 2", PartsType.STEERING, 23.2),
                new Part("part 3", PartsType.STEERING, 23.2),
                new Part("part 4", PartsType.STEERING, 23.2)
        );
        partService.addParts(parts);

        assertEquals("Lists are different", parts, partService.getParts());
    }

    @Test
    public void addParts() throws Exception {
        List<Part> parts = Arrays.asList(
                new Part("part 1", PartsType.STEERING, 23.2),
                new Part("part 2", PartsType.STEERING, 23.2),
                new Part("part 3", PartsType.STEERING, 23.2),
                new Part("part 4", PartsType.STEERING, 23.2)
        );
        partService.addParts(parts);

        assertEquals("size not equals", parts.size(), partService.getParts().size());

    }

    @Test
    public void saveJSON() throws Exception {
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void getByType() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}