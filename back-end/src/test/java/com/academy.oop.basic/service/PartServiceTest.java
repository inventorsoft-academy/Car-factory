package com.academy.oop.basic.service;

import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.dao.impl.PartDaoImpl;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.impl.PartServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartServiceTest {


    @Autowired
    private PartService partService;

    @Test
    public void addPart() throws Exception {

        Part part = new Part(PartsType.ENGINE, 88888.11, false);
        partService.addPart(part);
    }

    @Test
    public void getPartById() throws Exception {
    }

    @Test
    public void getParts() throws Exception {
        List<Part> parts = Arrays.asList(
                new Part(PartsType.STEERING, 23.2, false),
                new Part(PartsType.STEERING, 23.2, false),
                new Part(PartsType.STEERING, 23.2, false),
                new Part(PartsType.STEERING, 23.2, false)
        );
        partService.addParts(parts);

        assertEquals("Lists are different", parts, partService.getParts());
    }

    @Test
    public void addParts() throws Exception {
        List<Part> parts = Arrays.asList(
                new Part(PartsType.ENGINE, 23.2, false),
                new Part(PartsType.ENGINE, 23.2, false),
                new Part(PartsType.ENGINE, 23.2, false),
                new Part(PartsType.ENGINE, 23.2, false)
        );
        partService.addParts(parts);

        assertEquals("size not equals", parts.size(), partService.getParts().size());
    }
}