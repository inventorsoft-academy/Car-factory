package com.academy.oop.basic.controller;

import com.academy.oop.basic.model.Part;
import com.academy.oop.basic.service.PartService;
import com.google.common.collect.ImmutableList;
import com.sun.javafx.collections.ImmutableObservableList;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PartControllerTest {

    @Mock
    private PartService partService;

    @InjectMocks
    private PartController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createPart(@Mock Part part) throws SQLException, ClassNotFoundException {
        when(sut.createPart(part)).thenReturn(new ResponseEntity<>(part, HttpStatus.CREATED));
    }

    @Test
    void getPartById() {
    }

    @Test
    void getParts() throws SQLException {



    }

    @Test
    void updatePart() {
    }

    @Test
    void deletePartById() {
    }

}