package com.academy.oop.basic.dao.impl;

import com.academy.oop.basic.dao.SqlManager;
import com.academy.oop.basic.enums.PartsType;
import com.academy.oop.basic.model.Part;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostgresSqlManagerTest {

    private SqlManager sqlManager;
    private Part part;

    @Before
    public void setUp() throws Exception {
        sqlManager = new PostgresSqlManager();
        part = new Part(PartsType.ENGINE, 123.123, false);
    }

    @Test
    public void addPart() throws Exception {

        int partId = part.getPartId();

        sqlManager.addPart(part);

        assertEquals("Ids not equals", part, sqlManager.getPartById(partId));

    }

    @Test
    public void getParts() throws Exception {
        assertNotNull(sqlManager.getParts());
    }

    @Test
    public void getPartById() throws Exception {
        Part newPart = new Part(PartsType.SUSPENSION, 623.78, false);
        sqlManager.addPart(newPart);
    }

    @Test
    public void updatePart() throws Exception {
        Part part = new Part(PartsType.SUSPENSION, 132.44, false);

        sqlManager.addPart(part);
        Part partToUpdate = new Part(PartsType.STEERING, 444.55, true);

        sqlManager.updatePart(part.getPartId(), partToUpdate);

    }

    @Test
    public void deletePartById() throws Exception {
        sqlManager.deletePartById(4);
    }

}