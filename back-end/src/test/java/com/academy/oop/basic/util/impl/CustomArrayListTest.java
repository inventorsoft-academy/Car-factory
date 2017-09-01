package com.academy.oop.basic.util.impl;

import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class CustomArrayListTest {
    List<Part> parts;
    @Before
    public void setUp() throws Exception {
        parts = new CustomArrayList();
    }

    @Test
    public void add() throws Exception {

        Part part = new Part(PartsType.ENGINE, 123.123, false);

        parts.add(part);

        assertEquals(part, parts.get(0));
    }

    @Test
    public void addAtCurrentPosition() throws Exception {
        Part part1 = new Part(PartsType.ENGINE, 123.123, false);
        Part part2 = new Part(PartsType.ENGINE, 123.123, false);
        Part part3 = new Part(PartsType.STEERING, 123.123, false);

        parts.add(part2);
        parts.add(part1);
        parts.add(0, part3);


        System.out.println(parts.get(1).getType());


    }

}