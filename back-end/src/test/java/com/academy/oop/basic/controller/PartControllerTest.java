package com.academy.oop.basic.controller;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PartControllerTest {

    @Mock
    private PartService partService;

    @Mock
    private Part part;
//
//    @InjectMocks
//    private PartController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createPart() throws SQLException, ClassNotFoundException {
    }

    @Test
    void getPartById() {
    }

    @Test
    void getParts() throws SQLException {
        when(partService.getParts()).thenReturn(ImmutableList.of());
    }

    @Test
    void updatePart() {

    }

    @Test
    void deletePartById() {
    }

}