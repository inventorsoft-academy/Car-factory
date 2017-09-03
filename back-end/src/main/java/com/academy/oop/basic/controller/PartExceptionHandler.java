package com.academy.oop.basic.controller;

import com.academy.oop.basic.exception.NotEnoughPartsException;
import com.academy.oop.basic.util.impl.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class PartExceptionHandler {

    private Logger logger = Logger.getLogger(PartExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlException() {
        logger.error("Part not added. Check the fields!");
        return new ResponseEntity<>("Part not added, check the fields of your class.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<String> classNotFoundException() {
        logger.error("Class not found and where is he now?");
        return new ResponseEntity<>("Class not found and where is he now?", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerException() {
        logger.error("AAA, A NULL IS HERE!!!");
        return new ResponseEntity<>("null is a bad thing.", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<String> notEnoughPartsException() {
        logger.error("Not enough parts.");
        return new ResponseEntity<>("Not enough parts.", HttpStatus.CONFLICT);
    }
}
