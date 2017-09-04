package com.academy.oop.basic.service;

import com.academy.oop.basic.dao.PartDao;
import com.academy.oop.basic.exception.NotFoundException;
import com.academy.oop.basic.model.Part;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PartServiceTest {

    @Mock
    private PartService partService;

    @Autowired
    private Part part;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getPartsAtLeastOnce() throws SQLException {
        partService.getParts();
        verify(partService, atLeastOnce()).getParts();
    }

    @Test
    void getPartsList() throws SQLException {

    }

    @Test
    void getPartById() throws NotFoundException, SQLException, ClassNotFoundException {

    }

    @Test
    void addPart() throws SQLException, ClassNotFoundException {
        when(partService.addPart(part)).thenReturn(false);
    }

    @Test
    void deletePartById() {
    }

    @Test
    void updatePart() {
    }

}